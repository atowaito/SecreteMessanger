package com.shupeluter.message;

import java.security.PublicKey;

public interface MessageEncyptManager {
    String encryptMessage(String keyId ,String message);

    /**
     * keyIdで一意に決定される共通鍵を返却する。
     */
    PublicKey getPublickey(String keyId);

}
