package com.shupeluter.message;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class DefaultMessageEncryptManager extends AbsMessageEncryptManager {
    String KEY_PUBLIC = "src/test/resources/keys/public.der";
    String KEY_PRIVATE = "src/test/resources/keys/private.pk8";
    String SECRET_KEY_ID = "secret";

    

    @Override
    /**
     * keyIdから一意に 決定するファイルから共通鍵を生成する
     */
    public PublicKey getPublickey(String keyId) {
        Path keyFile = this.getFile(keyId);
        KeySpec keySpec = null;
        KeyFactory keyFactory;
        PublicKey publicKey = null;

        try {
            keySpec = new X509EncodedKeySpec(Files.readAllBytes(keyFile));
            keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
            e.printStackTrace();
        }

        return publicKey;

    }

    @Override
    public PrivateKey getPrivateKey(String id) {
        //FIXME IDで選べるように
        Path keyFile = this.getFile(SECRET_KEY_ID);
        KeySpec keySpec = null;
        KeyFactory keyFactory;
        PrivateKey privateKey=null;

        try {
            keySpec = new PKCS8EncodedKeySpec(Files.readAllBytes(keyFile));
            keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return privateKey;
    }

    protected Path getFile(String keyId) {
        // FIXME 処理は後から追加する
        if (SECRET_KEY_ID.equals(keyId)) {
            return Paths.get(KEY_PRIVATE);
        } else {
            return Paths.get(KEY_PUBLIC);
        }
    }
}
