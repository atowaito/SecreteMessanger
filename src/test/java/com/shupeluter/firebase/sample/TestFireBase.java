package com.shupeluter.firebase.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestFireBase {

    @BeforeClass
    public void initialize() throws FileNotFoundException{
        File file = new File("src\\main\\resources\\secretmessenger-97328-firebase-adminsdk-stelm-6775861b82.json");
        System.out.println(file.getAbsolutePath());
        FileInputStream serviceAccount = new FileInputStream(file);
        FirebaseOptions options;
        try {
            options = new FirebaseOptions.Builder().setCredentials(
                GoogleCredentials.fromStream(serviceAccount)
                ).build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }

}
