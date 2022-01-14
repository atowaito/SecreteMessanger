package com.shupeluter.message;


import java.security.PrivateKey;
import java.security.PublicKey;

public interface MessageEncyptManager {
    String encryptMessage(String keyId ,String message);
    String readMessage(String keyId, String message);
    
    /**
     * キーストアに共通鍵を登録する。
     */
    void registPublicKey(String keyId,PublicKey key);
    

    /**
     * keyIdで一意に決定される共通鍵を返却する。
     * @param keyId
     * @return
     */
    PublicKey getPublickey(String keyId);

    /**
     * keyIdで一意に決定される秘密鍵を返却する。
     * @param keyId
     * @return
     */
    PrivateKey getPrivateKey(String keyId);

}
