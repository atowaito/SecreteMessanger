package com.shupeluter.message;

import java.util.logging.Logger;

import com.google.firebase.messaging.Message;

import org.junit.Test;

public class BasicMessageManagerTest {

    MessageEncyptManager manager = new BasicMessageManager();
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Test
    public void testGetFile() {
       
    }

    @Test
    public void testGetPrivateKey() {
        manager.getPrivateKey("sample");
    }

    @Test
    public void testGetPublickey() {
        manager.getPublickey("sample");
    }

    @Test
    public void testRegistPublicKey() {

    }

    @Test
    public void 暗号化メッセージを作ってみる(){
        String encriptedMessage = manager.encryptMessage("sample", "あした学校に行きたいです。");
        logger.info(encriptedMessage);

        manager.readMessage("test",encriptedMessage);

    }
}
