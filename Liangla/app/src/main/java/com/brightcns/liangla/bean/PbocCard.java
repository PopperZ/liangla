/* NFCard is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 3 of the License, or
(at your option) any later version.

NFCard is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Wget.  If not, see <http://www.gnu.org/licenses/>.

Additional permission under GNU GPL version 3 section 7 */

package com.brightcns.liangla.bean;

import android.content.res.Resources;
import android.nfc.tech.IsoDep;

import com.brightcns.liangla.R;
import com.brightcns.liangla.utils.CardManager;
import com.brightcns.liangla.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 基卡
 */
public class PbocCard {
    protected final static byte[] DFI_MF = {(byte) 0x3F, (byte) 0x00};
    protected final static byte[] DFI_EP = {(byte) 0x10, (byte) 0x01};

    /**
     * 选择支付系统文件，它的名字是 1PAY.SYS.DDF01  select PSF (1PAY.SYS.DDF01)
     */
    protected final static byte[] DFN_PSE = {(byte) '1', (byte) 'P',
            (byte) 'A', (byte) 'Y', (byte) '.', (byte) 'S', (byte) 'Y',
            (byte) 'S', (byte) '.', (byte) 'D', (byte) 'D', (byte) 'F',
            (byte) '0', (byte) '1',};

    protected final static byte[] DFN_PXX = {(byte) 'P'};

    protected final static int MAX_LOG = 10; //最大值
    protected final static int SFI_EXTRA = 21;
    protected final static int SFI_LOG = 24;

    protected final static byte TRANS_CSU = 6;
    protected final static byte TRANS_CSU_CPX = 9;

    protected String name;
    protected String id;
    protected String serl;
    protected String version;// 版本
    protected String date;// 有效期
    protected String count;// 使用次数
    protected String cash;// 余额
    protected String log;// 交易日志

    public static String load(IsoDep tech, Resources res) {
        final Iso7816.Tag tag = new Iso7816.Tag(tech);

        tag.connect();

        PbocCard card = null;

        do {
            if ((card = ShenzhenTong.load(tag, res)) != null)
                break;

            if ((card = BeijingMunicipal.load(tag, res)) != null)
                break;

            if ((card = ChanganTong.load(tag, res)) != null)
                break;

            if ((card = WuhanTong.load(tag, res)) != null)
                break;

            if ((card = YangchengTong.load(tag, res)) != null)
                break;

            if ((card = HardReader.load(tag, res)) != null)
                break;

        } while (false);

        tag.close();

        return (card != null) ? card.toString(res) : null;
    }

    protected PbocCard(Iso7816.Tag tag) {
        id = tag.getID().toString();
    }

    /**
     * 解析 信息
     *
     * @param data
     * @param dec
     * @param bigEndian
     */
    protected void parseInfo(Iso7816.Response data, int dec, boolean bigEndian) {
        if (!data.isOkey() || data.size() < 30) {
            serl = version = date = count = null;
            return;
        }

        final byte[] d = data.getBytes();
        if (dec < 1 || dec > 10) {
            serl = Util.toHexString(d, 10, 10);
        } else {
            final int sn = bigEndian ? Util.toIntR(d, 19, dec) : Util.toInt(d,
                    20 - dec, dec);

            serl = String.format("%d", 0xFFFFFFFFL & sn);
        }

        version = (d[9] != 0) ? String.valueOf(d[9]) : null;
        date = String.format("%02X%02X.%02X.%02X - %02X%02X.%02X.%02X", d[20],
                d[21], d[22], d[23], d[24], d[25], d[26], d[27]);
        count = null;
    }

    protected static boolean addLog(final Iso7816.Response r,
                                    ArrayList<byte[]> l) {
        if (!r.isOkey())
            return false;

        final byte[] raw = r.getBytes();
        final int N = raw.length - 23;
        if (N < 0)
            return false;

        for (int s = 0, e = 0; s <= N; s = e) {
            l.add(Arrays.copyOfRange(raw, s, (e = s + 23)));
        }

        return true;
    }

    protected static ArrayList<byte[]> readLog(Iso7816.Tag tag, int sfi) {
        final ArrayList<byte[]> ret = new ArrayList<byte[]>(MAX_LOG);
        final Iso7816.Response rsp = tag.readRecord(sfi);
        if (rsp.isOkey()) {
            addLog(rsp, ret);
        } else {
            for (int i = 1; i <= MAX_LOG; ++i) {
                if (!addLog(tag.readRecord(sfi, i), ret))
                    break;
            }
        }

        return ret;
    }

    /**
     * 解析 交易日志
     *
     * @param logs
     */
    protected void parseLog(ArrayList<byte[]>... logs) {
        final StringBuilder r = new StringBuilder();

        for (final ArrayList<byte[]> log : logs) {
            if (log == null)
                continue;

            if (r.length() > 0)
                r.append("<br />--------------");

            for (final byte[] v : log) {
                final int cash = Util.toInt(v, 5, 4);
                if (cash > 0) {
                    r.append("<br />").append(
                            String.format("%02X%02X.%02X.%02X %02X:%02X ",
                                    v[16], v[17], v[18], v[19], v[20], v[21],
                                    v[22]));

                    final char t = (v[9] == TRANS_CSU || v[9] == TRANS_CSU_CPX) ? '-'
                            : '+';

                    r.append(t).append(Util.toAmountString(cash / 100.0f));

                    final int over = Util.toInt(v, 2, 3);
                    //[o:56.70]
                    if (over > 0)
                        r.append(" [o:")
                                .append(Util.toAmountString(over / 100.0f))
                                .append(']');
                    //[200011093118]
                    r.append(" [").append(Util.toHexString(v, 10, 6))
                            .append(']');
                }
            }
        }

        this.log = r.toString();
    }

    /**
     * 解析 余额
     *
     * @param data
     */
    protected void parseBalance(Iso7816.Response data) {
        if (!data.isOkey() || data.size() < 4) {
            cash = null;
            return;
        }

        int n = Util.toInt(data.getBytes(), 0, 4);
        if (n > 100000 || n < -100000)
            n -= 0x80000000;

        cash = Util.toAmountString(n / 100.0f);
    }

    /**
     * 填充 格式化信息 序号 版本 有效期 使用次数
     *
     * @param res
     * @return
     */
    protected String formatInfo(Resources res) {
        if (serl == null)
            return null;

        final StringBuilder r = new StringBuilder();

        //序号
        r.append(res.getString(R.string.lab_serl)).append(' ').append(serl);

        if (version != null) {
            //版本
            final String sv = res.getString(R.string.lab_ver);
            r.append("<br />").append(sv).append(' ').append(version);
        }

        if (date != null) {
            //有效期
            final String sd = res.getString(R.string.lab_date);
            r.append("<br />").append(sd).append(' ').append(date);
        }

        if (count != null) {
            //使用
            final String so = res.getString(R.string.lab_op);
            //次
            final String st = res.getString(R.string.lab_op_time);
            r.append("<br />").append(so).append(' ').append(count).append(st);
        }

        return r.toString();
    }

    /**
     * 填充 交易日志
     *
     * @param res
     * @return
     */
    protected String formatLog(Resources res) {
        if (log == null || log.length() < 1)
            return null;

        final StringBuilder ret = new StringBuilder();
        //交易记录
        final String sl = res.getString(R.string.lab_log);
        ret.append(sl);
        ret.append(log);

        return ret.toString();
    }

    /**
     * 填充 余额
     *
     * @param res 余额
     * @return
     */
    protected String formatBalance(Resources res) {
        if (cash == null || cash.length() < 1)
            return null;

        final String s = res.getString(R.string.lab_balance);
        final String c = res.getString(R.string.lab_cur_cny);
        return new StringBuilder()
                .append(s)
                .append(cash)
                .append(c).toString();
    }


    protected String toString(Resources res) {
        final String info = formatInfo(res);
        final String cash = formatBalance(res);
        final String hist = formatLog(res);

        return CardManager.buildResult(name, info, cash, hist);
    }
}
