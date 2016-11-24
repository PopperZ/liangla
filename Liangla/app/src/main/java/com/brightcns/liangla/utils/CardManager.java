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

Additional permission under GNU GPL version 3 section 7
 */

package com.brightcns.liangla.utils;

import android.content.IntentFilter;
import android.content.res.Resources;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Parcelable;
import android.util.Log;

import com.brightcns.liangla.bean.PbocCard;


/**
 * 卡片管理
 * 根据tag类型 选择不同的类
 */
public final class CardManager {
    //private static final String SP = "<br />------------------------------<br /><br />";
    //  private static final String SP = "<br />------------------------------</b><br />";

    public static String[][] TECHLISTS; //技术列表
    public static IntentFilter[] FILTERS;//过滤器

    /*
     B 声明你想要截获处理的Intent对象的Intent过滤器。
     前台调度系统会在设备扫描到NFC标签时，用声明的Intent过滤器来检查接收到的Intent对象。
     如果匹配就会让你的应用程序来处理这个Intent对象，如果不匹配，前台调度系统会回退到Intent调度系统。
     如果Intent过滤器和技术过滤器的数组指定了null，那么就说明你要过滤所有的退回到TAG_DISCOVERED类型的Intent对象的标签。
     以下代码会用于处理所有的NDEF_DISCOVERED的MIME类型。
     只有在需要的时候才做这种处理：
    */

//    IntentFilter ndef = newIntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
//    try{
//        ndef.addDataType("*/*");
//    }
//    catch(MalformedMimeTypeException e){
//        thrownewRuntimeException("fail", e);
//    }
//    intentFiltersArray=newIntentFilter[]{ndef,};


    /*

     C.  建立一个应用程序希望处理的NFC标签技术的数组。调用Object.class.getName()方法来获取你想要支持的技术的类：
    techListsArray = new String[][] { new String[] { NfcF.class.getName() } };

     */

    static {
        try {
            TECHLISTS = new String[][]{{IsoDep.class.getName()},
                    {NfcV.class.getName()}, {NfcF.class.getName()},};

            FILTERS = new IntentFilter[]{new IntentFilter(
                    NfcAdapter.ACTION_TECH_DISCOVERED, "*/*")};
        } catch (Exception e) {

        }
    }

    /**
     * 构建结果
     *
     * @param n 卡名字
     * @param d 余额
     * @param x 交易记录
     * @param i 序号 版本 生效
     * @return
     */
    public static String buildResult(String n, String i, String d, String x) {
        if (n == null)
            return null;

        final StringBuilder s = new StringBuilder();

        //s.append("<br/><b>").append(n).append("</b>");
        s.append(n);

        if (d != null)
            s.append(d);

        if (x != null)
            s.append(x);

        if (i != null)
            s.append(i);

        return s.toString();
    }

    /**
     * 根据不同的 tag类型 选择不同的类
     *
     * @param parcelable
     * @param res
     * @return
     */
    public static String load(Parcelable parcelable, Resources res) {
        final Tag tag = (Tag) parcelable;


        final IsoDep isodep = IsoDep.get(tag);
        Log.d("NFCTAG", "ffff");//isodep.transceive("45".getBytes()).toString());
        if (isodep != null) {
            return PbocCard.load(isodep, res);
        }


        final NfcV nfcv = NfcV.get(tag);
        if (nfcv != null) {
            return VicinityCard.load(nfcv, res);
        }


        final NfcF nfcf = NfcF.get(tag);
        if (nfcf != null) {
            return OctopusCard.load(nfcf, res);
        }

        return null;
    }
}
