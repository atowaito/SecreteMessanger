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
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BasicMessageManager extends AbsMessageEncryptManager {
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
    public void registPublicKey(String keyId, PublicKey key) {
        super.registPublicKey(keyId, key);
        DatabaseReference ref =  this.initalizeFirebase();
        ref.setValueAsync(keyId,key);
    }

    @Override
    public PrivateKey getPrivateKey(String id) {
        // FIXME IDで選べるように
        Path keyFile = this.getFile(SECRET_KEY_ID);
        KeySpec keySpec = null;
        KeyFactory keyFactory;
        PrivateKey privateKey = null;
        
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

    

    //TODO: Base64化して入れておくのがいいのか。もしくはBase64でエンコードしたファイルを入れておくのがいいのか悩みどころ
    private PublicKey getKeyByFirebase(String key){
        DatabaseReference ref = this.initalizeFirebase();
        return (PublicKey) ref.child(key);
    }

    protected Path getFile(String keyId) {
        // FIXME 処理は後から追加する
        if (SECRET_KEY_ID.equals(keyId)) {
            return Paths.get(KEY_PRIVATE);
        } else {
            return Paths.get(KEY_PUBLIC);
        }
    }

    private DatabaseReference initalizeFirebase() {
        FirebaseOptions options;
        try {
            options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .setDatabaseUrl("https://secretemessanger-default-rtdb.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("public-keys");
        return ref;

    }

}
