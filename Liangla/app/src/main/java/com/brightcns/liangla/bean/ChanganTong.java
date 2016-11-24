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


import com.brightcns.liangla.R;

import java.util.ArrayList;

/**
 * 上海交通卡
 */
final class ChanganTong extends PbocCard {
    // 选择公交卡应用的名字或者ID
    private final static byte[] DFN_SRV = {(byte) 0xA0, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x03, (byte) 0x86, (byte) 0x98,
            (byte) 0x07, (byte) 0x01,};

    private ChanganTong(Iso7816.Tag tag, Resources res) {
        super(tag);

        name = res.getString(R.string.name_cac);
    }

    @SuppressWarnings("unchecked")
    final static ChanganTong load(Iso7816.Tag tag, Resources res) {

		/*--------------------------------------------------------------*/
        // select PSF (1PAY.SYS.DDF01)
        // 选择PSF
        /*--------------------------------------------------------------*/
        if (tag.selectByName(DFN_PSE).isOkey()) {

            Iso7816.Response INFO, CASH;

			/*--------------------------------------------------------------*/
            // select Main Application
            // 选择卡的主应用
			/*--------------------------------------------------------------*/
            if (tag.selectByName(DFN_SRV).isOkey()) {

				/*--------------------------------------------------------------*/
                // read card info file, binary (21)
                // 读入卡的信息
				/*--------------------------------------------------------------*/
                INFO = tag.readBinary(SFI_EXTRA);

				/*--------------------------------------------------------------*/
                // read balance
				/*--------------------------------------------------------------*/
                CASH = tag.getBalance(true);

				/*--------------------------------------------------------------*/
                // read log file, record (24)
                // 读取文件日志 记录
				/*--------------------------------------------------------------*/
                ArrayList<byte[]> LOG = readLog(tag, SFI_LOG);

				/*--------------------------------------------------------------*/
                // build result string
                // 构建结果字符串
				/*--------------------------------------------------------------*/
                final ChanganTong ret = new ChanganTong(tag, res);
                ret.parseBalance(CASH);
                ret.parseInfo(INFO, 4, false);
                ret.parseLog(LOG);

                return ret;
            }
        }

        return null;
    }
}
