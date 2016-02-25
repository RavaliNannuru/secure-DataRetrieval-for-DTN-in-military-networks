package secure;

import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
public class DESedeEncryption {
 
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec myKeySpec;
    private SecretKeyFactory mySecretKeyFactory;
    private Cipher cipher;
    byte[] keyAsBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;
 
    public DESedeEncryption()
    {
    }

	public DESedeEncryption(String skey) throws Exception
    {
        myEncryptionKey = skey;//"This Is Secret Key";
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
		
        myKeySpec = new DESedeKeySpec(keyAsBytes);
        mySecretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = mySecretKeyFactory.generateSecret(myKeySpec);
    }
 
    public String encrypt(String unencryptedString) 
	{
        String encryptedString = null;
        try 
		{
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            BASE64Encoder base64encoder = new BASE64Encoder();
            encryptedString = base64encoder.encode(encryptedText);
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
        return encryptedString;
    }
    public String decrypt(String encryptedString) 
	{
        String decryptedText=null;
        try 
		{
            cipher.init(Cipher.DECRYPT_MODE, key);
            BASE64Decoder base64decoder = new BASE64Decoder();
            byte[] encryptedText = base64decoder.decodeBuffer(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= bytes2String(plainText);
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
        return decryptedText;
    }
    private static String bytes2String(byte[] bytes) 
	{
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i <bytes.length; i++) 
		{
            stringBuffer.append((char) bytes[i]);
        }
        return stringBuffer.toString();
    }
	
	public static String stringtoByte(String msg)
	{
        //System.out.println(msg);
        if(msg!=null && msg !="")
		{
        byte[] b = msg.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte c : b) 
		{
            int val = c;
            for (int i = 0; i < 8; i++) 
			{
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        System.out.println(binary);
    
        return binary.toString();
        }
		else
		{
            return "";
        }
	}
    
 
    public static void main(String args []) throws Exception
    {
		String stringToEncrypt="welcome";
		stringToEncrypt=stringtoByte(stringToEncrypt);
		DESedeEncryption myEncryptor= new DESedeEncryption("123456 123456 123456 123456");
		System.out.println("String To Encrypt: "+stringToEncrypt);
        String encrypted=myEncryptor.encrypt(stringToEncrypt);
		System.out.println("Encrypted Value : "+ encrypted);
        String decrypted=myEncryptor.decrypt(encrypted);
		System.out.println("Decrypted Value : "+decrypted);	
    }
 
}
