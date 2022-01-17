package com.shupeluter.firebase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shupeluter.firebase.sample.User;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConfirmFireBase {
    static Logger logger = Logger.getLogger("TEST");

    @Before
    public void initialize() throws IOException {
        logger.info(System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));

    }

    @Test
    public void dummy() throws InterruptedException, IOException {

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setDatabaseUrl("https://secretemessanger-default-rtdb.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                System.out.println(document);
            }

            @Override
            public void onCancelled(DatabaseError arg0) {
                // TODO Auto-generated method stub
                System.err.println("Canceled");
            }
        });

        DatabaseReference usersRef = ref.child("users");

        Map<String, User> users = new HashMap<>();
        users.put("hoge", new User("よしひろ", 15));
        usersRef.setValueAsync(users);

        Thread.sleep(5000);
    }
}
