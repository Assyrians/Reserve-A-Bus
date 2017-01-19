package com.nihal.reserveabus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "message";

    private List<Station> stationList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StationsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.stations_list);


        mAdapter = new StationsAdapter(stationList, new StationsAdapter.OnItemClickListener() {
            @Override public void onItemClick(Station station) {
                Intent intent = new Intent(MainActivity.this, JourneysActivity.class);
                intent.putExtra(EXTRA_MESSAGE, station.getStationName());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        prepareStationData();
    }
    private void prepareStationData() {
        Station station = new Station("Amman", "4 journeys");
        stationList.add(station);

        station = new Station("Irbid", "5 journeys");
        stationList.add(station);

        station = new Station("Zarqa", "6 journeys");
        stationList.add(station);



        mAdapter.notifyDataSetChanged();
    }
}
