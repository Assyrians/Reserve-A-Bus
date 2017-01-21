package com.nihal.reserveabus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "message";

    private List<Station> stationList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StationsAdapter mAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get database object :
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get database parent reference :
        databaseReference = database.getReference();


        recyclerView = (RecyclerView) findViewById(R.id.stations_list);


        mAdapter = new StationsAdapter(stationList, new StationsAdapter.OnItemClickListener() {
            @Override public void onItemClick(Station station) {
                Intent intent = new Intent(MainActivity.this, JourneysActivity.class);
                intent.putExtra(EXTRA_MESSAGE, station.getKey());
                intent.putExtra("stationName", station.getStationName());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String userId = user.getUid();
        prepareStationData();
    }
    private void prepareStationData() {
//        Station station = new Station("Amman", "4 journeys");
//        stationList.add(station);
//        databaseReference.child("Stations").push().setValue(station);
//
//        station = new Station("Irbid", "5 journeys");
//        stationList.add(station);
//        databaseReference.child("Stations").push().setValue(station);
//        station = new Station("Zarqa", "6 journeys");
//        stationList.add(station);
//        databaseReference.child("Stations").push().setValue(station);

        databaseReference.child("Stations").addChildEventListener(new ChildEventListener() {
            // Retrieve new posts as they are added to the database
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                Station station = snapshot.getValue(Station.class);
                station.setKey(snapshot.getKey());
                stationList.add(station);

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
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
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.logout_Id:
                // User chose the "Logout" action
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
