package com.shupeluter.message;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface MessageEncyptManager {
    String encryptMessage(String keyId ,String message);

    /**
     * keyIdで一意に決定される共通鍵を返却する。
     */
    PublicKey getPublickey(String keyId);

    /***
     * 自分の秘密鍵を返却する
     */
    PrivateKey getPrivateKey();

}
