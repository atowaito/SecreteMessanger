package com.shupeluter.message;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public abstract class AbsMessageEncryptManager implements MessageEncyptManager {

    @Override
    public String encryptMessage(String keyid, String message) {
        PublicKey key = this.getPublickey(keyid);
        return this.encryptMessage(key, message);
    }

    /**こっちが本体 */
    /**
     * 共通鍵を用いてメッセージを暗号化する。
     * @param key
     * @param message
     * @return
     */
    private String encryptMessage(PublicKey key,String message){
        
        try{
            Cipher cipher = Cipher.getInstance(PaddingType.DEFAULT.toString());
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] encriptedMessage = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String encriptedStrMessage=Base64.getEncoder().encodeToString(encriptedMessage);
            return encriptedStrMessage;
        } catch (NoSuchPaddingException e){
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";

    }

}
