package com.shupeluter.message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.shupeluter.util.AppException;

public class FireBaseEncryptManager extends AbsMessageEncryptManager{

    private FirebaseOptions options; 

    public FireBaseEncryptManager()throws AppException{
        this.initialize();
    }

    @Override
    public PrivateKey getPrivateKey(String keyId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PublicKey getPublickey(String keyId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void registPublicKey (String keyId, PublicKey key) throws AppException{
        try(
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos)){
            oos.writeObject(key);
            oos.flush();
            String encodedkey = Base64.getEncoder().encodeToString(bos.toByteArray());
            //TODO: 将来的に鍵オブジェクト→アプリの鍵で暗号化→エンコードするように変更する。
            this.registPublicKey(keyId, encodedkey);
            
        } catch(IOException ex){
            AppException aex = new AppException();
            aex.addSuppressed(ex);
            throw aex;
        }


    }

    private void registPublicKey(String id,String pubkey){

    }

    private void initialize()throws AppException{
        try {
            this.options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.getApplicationDefault())
            .setDatabaseUrl("https://secretemessanger-default-rtdb.firebaseio.com/")
            .build();
            FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            AppException ex = new AppException();
            ex.addSuppressed(e);
            throw ex;
        }
    }
}