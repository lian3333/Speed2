package com.example.speed13;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

// google explanations
// https://firebase.google.com/docs/database/android/lists-of-data#java_1


public class FB {
    private static FB instance;

    FirebaseDatabase database;
    private static Context context;

    private FB() {
        //database = FirebaseDatabase.getInstance("https://fbrecordssingletone-default-rtdb.firebaseio.com/");
        database = FirebaseDatabase.getInstance();

        listenerToOpen1();

        //this.records = MainActivity.records;

        // read the records from the Firebase and order them by the record from highest to lowest
        // limit to only 8 items
/*        Query myQuery = database.getReference("records").orderByChild("score").limitToLast(10);

        myQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                //records.clear();  // clear the array list
                MainActivity.records.clear();
                for(DataSnapshot userSnapshot : snapshot.getChildren())
                {
                    //String str =userSnapshot.child()  .getValue(Record.class);
                    Record currentRecord =userSnapshot.getValue(Record.class);
                    MainActivity.records.add(0, currentRecord);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/


    }



    public static FB getInstance(Context context1) {
        if (null == instance) {
            instance = new FB();
            context = context1;  // context to GameActivity הפניה
        }
        return instance;
    }

    public void setOpen1(Card card)
    {
        // Write a message to the database
        DatabaseReference myRef = database.getReference("open1"); // push adds new node with unique value
        FbCard fbCard = new FbCard(card.getValue(), 4);

        myRef.setValue(fbCard);
    }

    private void listenerToOpen1() {
        DatabaseReference myRef = database.getReference("open1"); // push adds new node with unique value

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FbCard fBcard = snapshot.getValue(FbCard.class);

                ((GameActivity)context).newValFromFbToOpen1(fBcard);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
