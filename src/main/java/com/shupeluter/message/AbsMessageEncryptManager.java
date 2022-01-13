package com.shupeluter.message;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public abstract class AbsMessageEncryptManager implements MessageEncyptManager {

    final Path PATH_KEY_STORE = Paths.get("./systemkeystore");
    final String PASSWORD_STORE = "password";

    KeyStore store;

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
        return this.readMessage(id, message);
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
    public void registPublicKey(String keyId, File keyFile) {
        // storeを保持していない場合生成する
        if (store == null) {
            try {
                store = KeyStore.getInstance("PKCS12");

                if (PATH_KEY_STORE.toFile().exists() && PATH_KEY_STORE.toFile().isFile()) {
                    try (FileInputStream stream = new FileInputStream(PATH_KEY_STORE.toFile())) {
                        store.load(
                                stream, PASSWORD_STORE.toCharArray());
                    }
                }
            } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {

                e.printStackTrace();
            }
        }

        //TODO 鍵を生成して、登録する。
    }
}
