package com.nihal.reserveabus;



import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StationsAdapter extends RecyclerView.Adapter<StationsAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Station station);
    }

    private List<Station> stationsList;
    private final OnItemClickListener listener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView stationName, journeysNumber;

        public MyViewHolder(View view) {
            super(view);
            stationName = (TextView) view.findViewById(R.id.stationName);
            journeysNumber = (TextView) view.findViewById(R.id.journeysNumber);
        }

        public void bind(final Station station, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(station);
                }
            });
        }

    }


    public StationsAdapter(List<Station> stationsList, OnItemClickListener listener) {
        this.stationsList = stationsList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.station_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Station station = stationsList.get(position);
        holder.bind(stationsList.get(position), listener);
        holder.stationName.setText(station.getStationName());
        holder.journeysNumber.setText(station.getJourneysNumber());
    }

    @Override
    public int getItemCount() {
        return stationsList.size();
    }
}