package modelo.conversores;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.jboss.resteasy.util.Base64;

@Converter(autoApply=true)
public class CryptoConverter implements AttributeConverter<String, String>{
	
	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";	
	private static final byte[] KEY = "wjf7dnc*3dh1bcfu".getBytes();
	    
	@Override
	public String convertToDatabaseColumn(String password) {
		
		// do Encryption
		Key key = new SecretKeySpec(KEY, "AES");
		
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
			
			return Base64.encodeBytes(c.doFinal(password.getBytes()));
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}

	@Override
	public String convertToEntityAttribute(String dbPasswordData) {
		/*
		// do Encryption
		Key key = new SecretKeySpec(KEY, "AES");
		
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
			
			return new String(c.doFinal(Base64.decode(dbPasswordData)));			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		*/
		return dbPasswordData;
	}	
}
