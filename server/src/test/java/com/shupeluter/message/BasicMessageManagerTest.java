package com.shupeluter.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.logging.Logger;

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
        logger.info(manager.readMessage("test", encriptedMessage));
        assertEquals("あした学校に行きたいです。",manager.readMessage("test",encriptedMessage));
        
    }
}
