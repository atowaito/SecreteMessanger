package com.shupeluter.firebase.sample;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

public class ConfirmJCA {
    Logger logger = Logger.getLogger(ConfirmJCA.class.getName());
    String KEY_PUBLIC="src/test/resources/keys/public.der";

    @Test
    public void OpenSSLの共通かぎファイルを読み込んでテキストを暗号化する() throws NoSuchAlgorithmException{
        logger.info("OpenSSLの共通かぎファイルを読み込む");
        Path path  = Paths.get(KEY_PUBLIC); //左辺は参照、右辺は実体のオブジェクト
    
        
        try {
            byte[] cihperBytes = this.generateMesageFile(path,"この文字を入れ替えて秘密メッセージにするんです");
            Files.write(Paths.get("encryptedByJCA.txt"), cihperBytes);
            logger.info("openssl rsautl -decrypt -inkey hoge.pem -in encryptedByJCA.txt");
        } catch (IOException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            fail();
        } 
        assertTrue(path.toFile().exists());
    } 

    private byte[] generateMesageFile(Path  keyfile,String message) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException{
        KeySpec keySpec = new X509EncodedKeySpec(Files.readAllBytes(keyfile));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey =  keyFactory.generatePublic(keySpec);

        //バイト列を暗号化
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        
    }

}
