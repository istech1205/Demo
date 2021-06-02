package com.istech.pixelmachinetest.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.istech.pixelmachinetest.R;
import com.istech.pixelmachinetest.adapter.DealsAdapter;
import com.istech.pixelmachinetest.databinding.ActivityTaskOneBinding;
import com.istech.pixelmachinetest.model.DealsOfTheModel;
import com.istech.pixelmachinetest.utils.Commn;
import com.istech.pixelmachinetest.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskOneActivity extends AppCompatActivity {
    private Context context;
    private TaskOneActivity activity;
    private ActivityTaskOneBinding binding;
    private DealsAdapter dealsAdapter = new DealsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_one);
        context = activity = this;
        handleclick();
        initData();
        getDeals();
    }

    private void handleclick() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        binding.rvDeals.setAdapter(dealsAdapter);
    }

    private void getDeals() {
        String url = "https://www.mlm.pixelsoftwares.com/vynkpay_store/api/dashboard";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            Commn.log("response:" + response.toString() + "");
            try {
                JSONObject jsonObject = new JSONObject(response);


                if (200 == jsonObject.getInt("status")) {
                    JSONObject productsJson = jsonObject.getJSONObject("products");
                    JSONArray dealsArray = productsJson.getJSONArray("dealsOfTheDay");
                    ArrayList<DealsOfTheModel> mList = new ArrayList<>();
                    for (int i = 0; i < dealsArray.length(); i++) {
                        JSONObject jsonObject1 = dealsArray.getJSONObject(i);
                        String product_name = jsonObject1.getString("product_name");
                        String mrp = jsonObject1.getString("mrp");
                        String sale_price = jsonObject1.getString("sale_price");
                        String hot_deal_img = jsonObject1.getString("images");

                        mList.add(new DealsOfTheModel(product_name, sale_price, mrp, hot_deal_img));
                    }
                    dealsAdapter.updateData(mList);
                }
            } catch (JSONException e) {
                Commn.log("JSONException:" + e.toString() + "");
                e.printStackTrace();
            }
        }, error -> {
            Commn.log("response:error" + error.toString() + "");
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Token", Const.token);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}