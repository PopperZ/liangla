package com.brightcns.liangla.utils;

/**
 * 工具类
 * Byte To String
 * Created by gengbeibei on 16/10/18.
 */

public class AboutByteToString {


    public static String bytesToString(byte[] bts, int off, int length) {
        StringBuffer str = new StringBuffer(length * 2);
        for (int i = 0; i < length; i++) {

            byte b = (byte) ((bts[i + off] >> 4) & 0x0F);
            str.append(byteToHexChar(b));


            byte c = (byte) (bts[i + off] & 0x0F);
            str.append(byteToHexChar(c));
        }

        return str.toString();
    }

    /**
     * byte 转换成 char
     *
     * @param b
     * @return
     */
    public static char byteToHexChar(byte b) {
        if (b > 0x0F) {
            throw new Error("Not a Hex Byte: " + b);
        }

        if ((b >= 0) && (b <= 9)) {
            return (char) (b + '0');
        } else {
            return (char) ((b - 0x0A) + 'A');
        }
    }

    /**
     * 将一个字节数组 转换成 一个十六进制的字符串
     *
     * @param bytes
     * @return
     */
    public static String ByteArrayToHexString(byte[] bytes) {
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * 将一个十六进制字符串 转换成 一个字节数组
     *
     * @param s 16进制表示的字符串
     * @return data 字节数组
     */
    public static byte[] HexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
