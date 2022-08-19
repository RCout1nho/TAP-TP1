package tp01.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword {
    public static String encrypt(String cleanPassword){
        try{
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(cleanPassword.getBytes());

            byte[] bytes = m.digest();

            StringBuilder s = new StringBuilder();

            for (byte aByte : bytes) {
                s.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            return s.toString();
        }catch (NoSuchAlgorithmException e){
            return null;
        }
    }
}
