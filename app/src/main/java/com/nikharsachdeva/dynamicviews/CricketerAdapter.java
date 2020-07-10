package com.nikharsachdeva.dynamicviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CricketerAdapter extends RecyclerView.Adapter<CricketerAdapter.CricketerView> {

    ArrayList<CricketerModel> cricketerModelArrayList = new ArrayList<>();

    public CricketerAdapter(ArrayList<CricketerModel> cricketerModelArrayList) {
        this.cricketerModelArrayList = cricketerModelArrayList;
    }

    @NonNull
    @Override
    public CricketerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_recycler,parent,false);


        return new CricketerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CricketerView holder, int position) {

        CricketerModel cricketerModel = cricketerModelArrayList.get(position);
        holder.cricketerName.setText(cricketerModel.getCricketerName());
        holder.cricketerTeam.setText(cricketerModel.getTeamName());


    }

    @Override
    public int getItemCount() {
        return cricketerModelArrayList.size();
    }

    public class CricketerView extends RecyclerView.ViewHolder{

        TextView cricketerName,cricketerTeam;

        public CricketerView(@NonNull View itemView) {
            super(itemView);

            cricketerName = itemView.findViewById(R.id.text_crickter_name);
            cricketerTeam = itemView.findViewById(R.id.text_team_name);

        }
    }
}
