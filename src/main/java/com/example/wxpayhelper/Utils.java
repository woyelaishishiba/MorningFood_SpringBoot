package com.example.wxpayhelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    public static String combineParam(String... input){
        String[] allInput = input;
        StringBuffer combine = new StringBuffer();
        for (int i = 0;i<allInput.length;i++){
            combine.append(allInput[i++] + "=");
            combine.append(allInput[i] + "&");
        }
        String ans = combine.toString().substring(0,combine.length()-1);
        return ans;
    }

    public static String getMD5String(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            return byteArrayToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String byteArrayToHex(byte[] byteArray) {

        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
        //一个byte是八位二进制，也就是2位十六进制字符
        char[] resultCharArray =new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b& 0xf];
        }
        return new String(resultCharArray);

    }

}
