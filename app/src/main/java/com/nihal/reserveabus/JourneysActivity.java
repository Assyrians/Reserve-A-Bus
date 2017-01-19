package com.nihal.reserveabus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class JourneysActivity extends AppCompatActivity {

    private List<Journey> journeysList = new ArrayList<>();
    private RecyclerView recyclerView;
    private JourneysAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journeys);
        recyclerView = (RecyclerView) findViewById(R.id.journeys_list);


        mAdapter = new JourneysAdapter(journeysList, new JourneysAdapter.OnItemClickListener() {
            @Override public void onItemClick(Journey journey) {
                Toast.makeText(getApplicationContext(), "WoW", Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareJourneysData();
    }
    private void prepareJourneysData() {
        Journey journey = new Journey("Amman", "433");
        journeysList.add(journey);

        journey = new Journey("Irbid", "522");
        journeysList.add(journey);

        journey = new Journey("Zarqa", "655");
        journeysList.add(journey);



        mAdapter.notifyDataSetChanged();
    }
}
