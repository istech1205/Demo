package com.istech.pixelmachinetest.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.istech.pixelmachinetest.R;
import com.istech.pixelmachinetest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivity activity;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        context = activity = this;
        handleclick();
    }

    private void handleclick() {
        binding.tvDeals.setOnClickListener(v -> startActivity(new Intent(context, TaskOneActivity.class)));
        binding.tvDeals2.setOnClickListener(v -> startActivity(new Intent(context, TaskTwoActivity.class)));
    }
}