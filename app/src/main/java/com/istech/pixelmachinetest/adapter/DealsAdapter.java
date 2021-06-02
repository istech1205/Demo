package com.istech.pixelmachinetest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.istech.pixelmachinetest.R;
import com.istech.pixelmachinetest.databinding.DealsOfLayoutBinding;
import com.istech.pixelmachinetest.model.DealsOfTheModel;

import java.util.ArrayList;
import java.util.List;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.ViewHolder> {

    public ArrayList<DealsOfTheModel> mList = new ArrayList<>();
    private Context context;


    @NonNull
    @Override
    public DealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.deals_of_layout, parent, false);
        return new DealsAdapter.ViewHolder(view);
    }


    public void updateData(List<DealsOfTheModel> list) {
        mList = (ArrayList<DealsOfTheModel>) list;
        notifyDataSetChanged();
    }

    public void loadMore(List<DealsOfTheModel> data) {
        mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull DealsAdapter.ViewHolder holder, int position) {

        DealsOfTheModel model = mList.get(position);

        Glide.with(context)
                .load(model.getProduct_image())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.07f)

                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.binding.ivProductImage);

        holder.binding.tvProductMaxPrice.setText("$" + model.getSale_price());
        holder.binding.tvProductMinPrice.setText("$" + model.getMrp());
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private DealsOfLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            if (binding != null) {
                binding.executePendingBindings();
            }
        }
    }


}

