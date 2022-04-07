package com.shupeluter.message;


import java.security.PrivateKey;
import java.security.PublicKey;

import com.shupeluter.util.AppException;

public interface MessageEncyptManager {
    String encryptMessage(String keyId ,String message);
    String readMessage(String keyId, String message);
    
    /**
     * キーストアに共通鍵を登録する。
     */
    void registPublicKey(String keyId,PublicKey key) throws AppException;

    /**
     * キーストアに秘密鍵を登録する
     * @param keyId
     * @param key
     * @throws AppException
     */
    public void registSecretKey(String keyId, PrivateKey key) throws AppException;
    
    /**
     * keyIdで一意に決定される共通鍵を返却する。
     * @param keyId
     * @return
     */
    public PublicKey getPublickey(String keyId);

    /**
     * keyIdで一意に決定される秘密鍵を返却する。
     * @param keyId
     * @return
     */
    public PrivateKey getPrivateKey(String keyId);

    /**キーペアを作成してIDに紐づけて登録する */
    public void generateKeyPair(String keyid)throws AppException;
}
