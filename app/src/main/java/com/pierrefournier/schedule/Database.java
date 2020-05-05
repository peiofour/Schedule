package com.pierrefournier.schedule;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Database {
    private final FirebaseFirestore db;
    private final CollectionReference usersCollection;

    public Database(){
        db = FirebaseFirestore.getInstance();
        usersCollection = db.collection("users");
    }

    public CollectionReference getUsersCollection() {
        return usersCollection;
    }

    public DocumentReference getUserRef(String ref){
        return usersCollection.document(ref);
    }

}
