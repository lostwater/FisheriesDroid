package com.xyxd.fisher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.xyxd.fisher.Fragment.OrderFragment;
import com.xyxd.fisher.Http.Client;
import com.xyxd.fisher.R;
import com.xyxd.fisher.model.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MyOrdersActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener,
        OrderFragment.OnListFragmentInteractionListener,
        SwipeRefreshLayout.OnRefreshListener

{
    OrderFragment orderFragment;
        List<Order> orderList = new ArrayList();
    Spinner timeSpin;
    Spinner statuSpin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        orderFragment = (OrderFragment)getSupportFragmentManager().findFragmentById(R.id.orders_fragment);
        timeSpin=(Spinner)findViewById(R.id.timeSpinner);
//createFromResouce将返回ArrayAdapter<CharSequence>，具有三个参数：
        //第一个是conetxt，也就是application的环境，可以设置为this，也可以通过getContext()获取.
        //第二个参数是从data source中的array ID，也就是我们在strings中设置的ID号；
        //第三个参数是spinner未展开的UI格式
        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this, R.array.ordertime_arry, android.R.layout.simple_spinner_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpin.setAdapter(timeAdapter);
        timeSpin.setOnItemSelectedListener(this);

        statuSpin=(Spinner)findViewById(R.id.statuSpinner);
//createFromResouce将返回ArrayAdapter<CharSequence>，具有三个参数：
        //第一个是conetxt，也就是application的环境，可以设置为this，也可以通过getContext()获取.
        //第二个参数是从data source中的array ID，也就是我们在strings中设置的ID号；
        //第三个参数是spinner未展开的UI格式
        ArrayAdapter<CharSequence> statuAdapter = ArrayAdapter.createFromResource(this, R.array.orderstatu_arry, android.R.layout.simple_spinner_item);
        statuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statuSpin.setAdapter(statuAdapter);
        statuSpin.setOnItemSelectedListener(this);



    }

    void setOrderList()
    {
        try {
            orderList = Client.userClient().getOrders(timeSpin.getSelectedItemPosition(),statuSpin.getSelectedItemPosition()).execute().body();
        }
        catch (IOException e){}

    }

    void loadOrders()
    {
        int timeId = timeSpin.getSelectedItemPosition();
        int statuId = statuSpin.getSelectedItemPosition();
        Client.userClient().getOrders(timeId,statuId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Response<List<Order>> response) {
                orderFragment.loadData(response.body());
                orderFragment.endLoad();
            }
            @Override
            public void onFailure(Throwable t) {
                orderFragment.endLoad();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        orderFragment.beginLoad();
        loadOrders();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    @Override
    public void onListFragmentInteraction(Object item) {
        Intent intent = new Intent(MyOrdersActivity.this, OrderActivity.class);
        Gson gson = new Gson();
        String str = gson.toJson((Order)item);
        intent.putExtra("order",str);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        loadOrders();
    }
}
