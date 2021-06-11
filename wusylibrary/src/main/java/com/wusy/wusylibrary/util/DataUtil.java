package com.wusy.wusylibrary.util;

import java.security.MessageDigest;
import java.text.DecimalFormat;

/**
 * 这是一个数据转化工具类
 */
public class DataUtil {
    /**
     * 判断传入Object是否为空
     */
    public static boolean isNull(Object obj) {
        if (obj == null || obj.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 文本转MD5
     */
    public static String strToMD5(String plaintext) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };
        try {
            byte[] btInput = plaintext.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 金钱格式化
     */
    public static String formatMoney(Double data) {
        if (data == 0.0) return "0.00";
        DecimalFormat df = new DecimalFormat("#,###.00");
        return "￥" + df.format(data);
    }

    /**
     * 格式化百分数
     */
    public static String formatPresent(float item) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(item * 100) + "%";
    }

    /**
     * 格式化小数点两位
     */
    public static String formatPoint(float item) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(item);
    }

    /**
     * 字节数组转转hex字符串，可选长度
     */
    public static String byteToHex(byte[] b, int length) {
        String keyVal = "";
        for (int i = 0; i < length; i++) {
            String temp = Integer.toHexString(b[i] & 0xFF);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            keyVal = keyVal + temp;
        }
        return keyVal.toUpperCase();
    }

    /**
     * 将16进制字符串转化为byte数组
     */
    public static byte[] HexString2Bytes(String src) {
        int len = src.length() / 2;
        byte[] ret = new byte[len];
        byte[] tmp = src.getBytes();
        for (int i = 0; i < len; i++) {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }

    private static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue();
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }

    /**
     * Hex字符串转int
     */
    public static int HexToInt(String inHex) {
        return Integer.parseInt(inHex, 16);
    }

    /**
     * int字符串转Hex
     */
    public static String IntToHex(int intHex) {
        return Integer.toHexString(intHex);
    }
}
