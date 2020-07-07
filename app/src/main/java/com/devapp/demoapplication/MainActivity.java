package com.devapp.demoapplication;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devapp.demoapplication.Adapter.GetData_Adapter;
import com.devapp.demoapplication.Interface.ApiInterface;
import com.devapp.demoapplication.Retrofit.ApiClient;
import com.devapp.demoapplication.model.Datum;
import com.devapp.demoapplication.model.GettingData;
import com.devapp.demoapplication.util.BaseActivity;
import com.devapp.demoapplication.util.Connectivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    RecyclerView recyclerView;
    GetData_Adapter adapter;
    List<Datum> list;
    Button Retrybtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_view);
        Retrybtn = findViewById(R.id.retry);
        Retrybtn.setOnClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        getData();
    }


    public void getData() {
        if(Connectivity.isConnectedMobile(this) || (Connectivity.isConnectedWifi(this))) {
            showLoading();
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            Call<GettingData> call = apiService.getList();
            call.enqueue(new Callback<GettingData>() {
                @Override
                public void onResponse(Call<GettingData> call, Response<GettingData> response) {
                    Retrybtn.setVisibility(View.GONE);
                    if (response.body() != null) {
                        GettingData data = response.body();
                        list = data.getData();
                        prepareRecyclerView(list);
                        hideLoading();
                    } else {
                        showSnackBar("Something went Wrong");
                    }
                }

                @Override
                public void onFailure(Call<GettingData> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                    showSnackBar("Something went Wrong,Please try again later");
                }
            });
        }
        else
        {
            Retrybtn.setVisibility(View.VISIBLE);
            showSnackBar("Internet Not Available, please Enable Internet Connection");
        }

    }


    private void prepareRecyclerView(List<Datum> list) {
        adapter = new GetData_Adapter(MainActivity.this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.retry:
                getData();
        }
    }
}
