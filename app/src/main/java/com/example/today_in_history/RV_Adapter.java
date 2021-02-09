package com.example.today_in_history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RV_Adapter extends RecyclerView.Adapter<RV_Adapter.RV_Viewholder> {
    private List<RvData> list;

    public RV_Adapter(List<RvData> list){
        this.list = list;
    }

    class RV_Viewholder extends RecyclerView.ViewHolder{
        public TextView year;
        public TextView monthday;
        public TextView type;
        public TextView title;
        public TextView desc;

        public RV_Viewholder(@NonNull View itemView){
            super(itemView);
            year = itemView.findViewById(R.id.year);
            monthday = itemView.findViewById(R.id.monthday);
            type = itemView.findViewById(R.id.type);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
        }
    }

    @Override
    public RV_Viewholder onCreateViewHolder(ViewGroup parent,int viewType){
        return new RV_Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,null));
}

    @Override
    public void onBindViewHolder(@NonNull RV_Viewholder holder,int position){
        RvData rvData = list.get(position);
        holder.year.setText(rvData.getYear());
        holder.monthday.setText(rvData.getMonthday());
        holder.type.setText(rvData.getType());
        holder.title.setText(rvData.getTitle());
        holder.desc.setText(rvData.getDesc());
    }

    @Override
    public int getItemCount(){
        return list.size();
    }
}
