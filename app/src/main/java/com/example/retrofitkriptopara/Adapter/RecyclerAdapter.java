package com.example.retrofitkriptopara.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitkriptopara.Model.CryptoModel;
import com.example.retrofitkriptopara.databinding.RowLayoutBinding;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<CryptoModel> modelArrayList;

    private String[] colors = {"#82E0AA","#F7DC6F","#A569BD","#85C1E9","#F9E79F","#F1948A"};

    public RecyclerAdapter(ArrayList<CryptoModel> modelArrayList) {
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RowLayoutBinding rowLayoutBinding = RowLayoutBinding.inflate(inflater,parent,false);
        return new ViewHolder(rowLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(modelArrayList.get(position),colors, position);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowLayoutBinding rowLayoutBinding;

        public ViewHolder(@NonNull RowLayoutBinding rowLayoutBinding) {
            super(rowLayoutBinding.getRoot());
            this.rowLayoutBinding = rowLayoutBinding;
        }

        public void bindView(CryptoModel cryptoModel, String[] colors, int position){
            itemView.setBackgroundColor(Color.parseColor(colors[position%6]));
            rowLayoutBinding.textCurrency.setText(cryptoModel.getCurrency());
            rowLayoutBinding.textPrice.setText(cryptoModel.getPrice());
        }

    }
}
