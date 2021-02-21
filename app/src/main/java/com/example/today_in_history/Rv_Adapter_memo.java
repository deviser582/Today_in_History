package com.example.today_in_history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Rv_Adapter_memo extends RecyclerView.Adapter<Rv_Adapter_memo.RvViewHolder>{

    private List<Rv_Data_memo> list;

    private ItemClickListener itemClickListener;

    public Rv_Adapter_memo(List<Rv_Data_memo> list) {
        this.list = list;
    }

    class RvViewHolder extends RecyclerView.ViewHolder {
        public TextView rv_1;
        public Button delete;

        public RvViewHolder(@NonNull View itemView) {
            super(itemView);
            rv_1 = itemView.findViewById(R.id.rv_1);
            //传入接口，复写子item的点击事件
            delete = itemView.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener != null){
                        itemClickListener.OnClick(v,getPosition());
                    }
                }
            });
            delete.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(itemClickListener != null){
                        itemClickListener.OnLongClick(v,getPosition());
                    }
                    return false;
                }
            });
        }
    }

    @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RvViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_memo,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHolder holder, int position) {
        Rv_Data_memo rvData = list.get(position);
        holder.rv_1.setText(rvData.getMemo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
