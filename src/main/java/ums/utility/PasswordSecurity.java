package ums.utility;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class PasswordSecurity {
	
	
	private static final String ALGORITHM = "AES";
    private byte[] keyValue;
    private Key securityKey;
    
    
    public PasswordSecurity() throws Exception{
//    	securityKey = generateKey();
    	keyValue = "1234567891234567".getBytes("UTF-8");
    	securityKey = new SecretKeySpec(keyValue, ALGORITHM);
    	
    }
	
//    private static Key generateKey() throws Exception {
//        Key key = new SecretKeySpec(keyValue, ALGORITHM);
//        return key;
//    }
//	
	public String encrypt(String valueToEnc) throws Exception {
	       Cipher cipher = Cipher.getInstance(ALGORITHM);
	       cipher.init(Cipher.ENCRYPT_MODE, securityKey);
	       
	       byte[] encValue = cipher.doFinal(valueToEnc.getBytes("UTF-8"));
	       byte[] encryptedByteValue = new Base64().encode(encValue);
	 
	       return new String(encryptedByteValue, "UTF-8");
	   }
	
	public String decrypt(String encryptedValue) throws Exception {
	        //Key key = generateKey();
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, securityKey);
	        
	        byte[] decodedBytes = new Base64().decode(encryptedValue.getBytes("UTF-8"));
	 
	        byte[] enctVal = cipher.doFinal(decodedBytes);
	        
	        return new String(enctVal, "UTF-8");
	    }
	
}