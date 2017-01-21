package com.nihal.reserveabus;



import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class JourneysAdapter extends RecyclerView.Adapter<JourneysAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Journey journey);
    }

    private List<Journey> journeysList;
    private final OnItemClickListener listener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView stationName, busNumber;
        public TextView arrivalTime;
        public TextView available;
        public TextView leaveTime;



        public MyViewHolder(View view) {
            super(view);
            stationName = (TextView) view.findViewById(R.id.stationName);
            busNumber = (TextView) view.findViewById(R.id.busNumber);
//
            arrivalTime = (TextView) view.findViewById(R.id.arrivalTime);
            available = (TextView) view.findViewById(R.id.available);
            leaveTime = (TextView) view.findViewById(R.id.leaveTime);

        }

        public void bind(final Journey journey, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(journey);
                }
            });
        }

    }


    public JourneysAdapter(List<Journey> journeysList, OnItemClickListener listener) {
        this.journeysList = journeysList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.journey_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Journey journey = journeysList.get(position);
        holder.bind(journeysList.get(position), listener);
        holder.stationName.setText(journey.getStationName());
        holder.busNumber.setText(journey.getBusNumber());

        holder.arrivalTime.setText("Arrival Time: " + String.valueOf(journey.getArrivalDate().getHours() + ":00"));
        holder.leaveTime.setText("Moving Time: " + String.valueOf(journey.getDate().getHours() + ":00"));
        if(journey.getStudents() == null)
            journey.setStudents(new ArrayList<String>());
        int availableText =  journey.getCapacity() - journey.getStudents().size();
        holder.available.setText(String.valueOf(availableText));
    }

    @Override
    public int getItemCount() {
        return journeysList.size();
    }
}