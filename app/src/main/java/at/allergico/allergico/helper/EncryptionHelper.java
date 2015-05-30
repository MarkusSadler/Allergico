package at.allergico.allergico.helper;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by Markus on 30.05.2015.
 */
public class EncryptionHelper {
    /******** SINGLETON START ********/
    private static EncryptionHelper _instance;

    public static EncryptionHelper get_instance() {
        if(_instance == null){_instance = new EncryptionHelper();}
        return _instance;
    }
    /******** SINGLETON END *********/

    /***
     * Key for the DES Algorithm
     * Generated with Keepass 2 on 30.05.2015 Settings: 128 length, Upper and Lower Case, Numbers
     */
    private static final String KEY = "Ntuad6g3ngx7JclA2RrJ3hAiWsF5JoIaE0E8vmFQ2afRZtOSMYDGTcbzm1r1IhlwfDFeWF2NTSrjDG1qnBhPbwyQeMJAXwfnXdN4erX3luQlHsVTRIMJ3uJXb7nNqmrQ";

    /***
     * Encrypts the given String with the DES Algorithm and returns the Encrypted hash as a String
     * The given String needs to be in UTF-8.
     * @param clearText
     * @return encryptedString
     */
    public String encryptStringWithDES(String clearText){
        try {
            DESKeySpec keySpec = new DESKeySpec(KEY.getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] byteArr = clearText.getBytes("UTF8");
            return Base64.encodeToString(cipher.doFinal(byteArr), Base64.DEFAULT);
        }catch (BadPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
