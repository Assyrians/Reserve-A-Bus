package com.nihal.reserveabus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JourneysActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "message";
    private List<Journey> journeysList = new ArrayList<>();
    private RecyclerView recyclerView;
    private JourneysAdapter mAdapter;
    DatabaseReference databaseReference;
    String stationKey;
    String stationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journeys);
//        Intent intent = getIntent();
        stationKey = getIntent().getStringExtra(EXTRA_MESSAGE);
        stationName = getIntent().getStringExtra("stationName");


        // Get database object :
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get database parent reference :
        databaseReference = database.getReference();


        recyclerView = (RecyclerView) findViewById(R.id.journeys_list);


        mAdapter = new JourneysAdapter(journeysList, new JourneysAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Journey journey) {
                Toast.makeText(getApplicationContext(), journey.getKey(), Toast.LENGTH_SHORT).show();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userId = user.getUid();
                if(journey.getStudents() == null)
                    journey.setStudents(new ArrayList<String>());
                if(journey.getStudents().indexOf(userId) == -1){
                    journey.AddStudent(userId);
                    databaseReference.child("Journeys").child(stationKey).setValue(journey);
                }

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareJourneysData();
    }

    private void prepareJourneysData() {
        final Date now = new Date();

//      Generate Data :
//        final Date now = new Date();
//        for (int j = 0; j <= 7; j++) {
//            for (int i = 15; i <= 18; i++) {
//                if (now.getMonth() + j <= 31) {
//
//
//                    Date date = new Date(now.getYear(), now.getMonth(), now.getDate() + j, i, 0);
//
//                    Date arrivaldate = new Date(now.getYear(), now.getMonth(), now.getDate() + j, i - 1, 30);
//
//                    Journey journey = new Journey(stationName, "Bus : " + i, stationKey, 20, date, arrivaldate);
//                    journeysList.add(journey);
//
//                    databaseReference.child("Journeys").push().setValue(journey);
//                }
//            }
//        }


        databaseReference.child("Journeys")
                .orderByChild("stationKey").equalTo(stationKey)
                .addChildEventListener(new ChildEventListener() {
                    // Retrieve new posts as they are added to the database
                    @Override
                    public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                        Journey journey = snapshot.getValue(Journey.class);
                        if (journey.getDate().getDate() == now.getDate() &&  journey.getDate().getHours() > now.getHours()) {
                            journey.setKey(snapshot.getKey());
                            journeysList.add(journey);
                        }

                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        Journey journey = dataSnapshot.getValue(Journey.class);
                        if (journey.getDate().getDate() == now.getDate() &&  journey.getDate().getHours() > now.getHours()) {
                            journey.setKey(dataSnapshot.getKey());
                            journeysList.add(journey);
                        }

                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }


                });


        mAdapter.notifyDataSetChanged();
    }
}
