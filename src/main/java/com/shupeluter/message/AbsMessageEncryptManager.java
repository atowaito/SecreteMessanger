package com.shupeluter.message;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.shupeluter.util.AppException;

public abstract class AbsMessageEncryptManager implements MessageEncyptManager {

   
    final String PASSWORD_STORE = "password";
    @Override
    public String encryptMessage(String keyid, String message) {
        PublicKey key = this.getPublickey(keyid);
        return this.encryptMessage(key, message);
    }

    /** こっちが本体 */
    /**
     * 共通鍵を用いてメッセージを暗号化する。
     * 
     * @param key
     * @param message
     * @return
     */
    private String encryptMessage(PublicKey key, String message) {

        try {
            Cipher cipher = Cipher.getInstance(PaddingType.DEFAULT.toString());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encriptedMessage = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String encriptedStrMessage = Base64.getEncoder().encodeToString(encriptedMessage);
            return encriptedStrMessage;
        } catch (NoSuchPaddingException e) {
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

    /**
     * このメソッドは受信側での利用もしくは、テストでの利用を想定している
     * メッセージ送信側で鍵をはずして送信する方式を選択してはならない。
     * 
     * @return
     * 
     */
    @Deprecated
    public String readMessage(String id, String message) {
        PrivateKey privateKey = this.getPrivateKey(id);
        if(privateKey !=null){
            return this.readMessage(privateKey, message);
        }
        return "";
    }

    String readMessage(PrivateKey privatekey, String message) {
        try {
            byte[] messageByte = Base64.getDecoder().decode(message);
            Cipher cipher = Cipher.getInstance(PaddingType.DEFAULT.toString());
            cipher.init(Cipher.DECRYPT_MODE, privatekey);
            byte[] decriptedMessage = cipher.doFinal(messageByte);
            return new String(decriptedMessage, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void generateKeyPair(String keyid) throws AppException{
        // TODO Auto-generated method stub
        try{
            KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
            keygen.initialize(2024);
            KeyPair pair = keygen.generateKeyPair();
            
            this.registPublicKey(keyid, pair.getPublic());
            this.registSecretKey(keyid, pair.getPrivate());
            
        } catch (NoSuchAlgorithmException ex){
            AppException aex = new AppException();
            aex.addSuppressed(ex);
            throw aex;
        }
    }

    @Override
    public PrivateKey getPrivateKey(String keyId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PublicKey getPublickey(String keyId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void registPublicKey(String keyId, PublicKey key) throws AppException {
        // TODO Auto-generated method stub
 
    }


}
