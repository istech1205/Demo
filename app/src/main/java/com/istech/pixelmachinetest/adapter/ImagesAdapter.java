package com.istech.pixelmachinetest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.istech.pixelmachinetest.R;
import com.istech.pixelmachinetest.databinding.ImagesLayoutBinding;
import com.istech.pixelmachinetest.model.ImagesBitmap;

import java.util.ArrayList;
import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    public ArrayList<ImagesBitmap> mList = new ArrayList<>();
    private Context context;


    @NonNull
    @Override
    public ImagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.images_layout, parent, false);
        return new ImagesAdapter.ViewHolder(view);
    }


    public void updateData(List<ImagesBitmap> list) {
        mList = (ArrayList<ImagesBitmap>) list;
        notifyDataSetChanged();
    }

    public void loadMore(List<ImagesBitmap> data) {
        mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesAdapter.ViewHolder holder, int position) {

        ImagesBitmap model = mList.get(position);

        holder.binding.ivProductImage.setImageBitmap(model.getImage());

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImagesLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            if (binding != null) {
                binding.executePendingBindings();
            }
        }
    }


}

