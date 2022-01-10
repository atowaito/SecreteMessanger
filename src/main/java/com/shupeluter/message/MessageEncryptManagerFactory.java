package com.shupeluter.message;

public interface MessageEncryptManagerFactory {
    public  static MessageEncyptManager getManager(){
        return new DefaultMessageEncryptManager();
    }
}
