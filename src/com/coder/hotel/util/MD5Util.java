package com.coder.hotel.util;

import java.security.MessageDigest;

/**
 * @author teacher_shi
 */
public class MD5Util {
    public static void main(String[] args) {
        System.out.println(getPWD("222222"));
    }
    public static String getPWD( String strs ){
        StringBuffer sb = new StringBuffer();
        try{
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bs = digest.digest(strs.getBytes());
            for (byte b : bs) {
                int x = b & 255;
                String s = Integer.toHexString(x);
                if( x > 0 && x < 16 ){
                    sb.append("0");
                    sb.append(s);
                }else{
                    sb.append(s);
                }
            }

        }catch( Exception e){
            System.out.println("加密失败");
        }
        return sb.toString();
    }
}
