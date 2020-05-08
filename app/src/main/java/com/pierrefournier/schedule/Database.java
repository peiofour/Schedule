package com.pierrefournier.schedule;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class Database {
    private final CollectionReference usersCollection;

    public Database(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        usersCollection = db.collection("users");
    }

    public CollectionReference getUsersCollection() {
        return usersCollection;
    }

    public DocumentReference getUserRef(String ref){
        return usersCollection.document(ref);
    }

}
