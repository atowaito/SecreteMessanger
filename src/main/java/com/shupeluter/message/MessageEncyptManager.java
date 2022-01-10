package com.shupeluter.message;

import java.security.PublicKey;

public interface MessageEncyptManager {
    String encryptMessage();
    PublicKey getPublickey(String keyId);
        
}
