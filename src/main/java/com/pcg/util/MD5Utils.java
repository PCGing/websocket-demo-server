package com.pcg.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * com.maichu8.p20.util
 * 2019-03-22 11:02 By marshall.
 */
public class MD5Utils {

    public static String getMd5(String plainText) {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(plainText.getBytes());
            byte[] b = e.digest();
            StringBuffer buf = new StringBuffer();
            for(int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if(i < 0) {
                    i += 256;
                }
                if(i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException var6) {
            var6.printStackTrace();
            return null;
        }
    }
}
