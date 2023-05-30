package com.example.mykiosk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mykiosk.model.Menu;
import com.example.mykiosk.model.Order;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4;

    private ArrayList<Order> orderList = new ArrayList<Order>();
    private RecyclerView recyclerView;
    public OrderAdapter orderAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment1 fragment1 = new Fragment1();
//        fragment1.setOrder(orderList); // order 리스트 전달
        transaction.replace(R.id.frame,fragment1);
        transaction.commit();

        btn1=(Button) findViewById(R.id.menu1);
        btn2=(Button) findViewById(R.id.menu2);
        btn3=(Button) findViewById(R.id.menu3);
        btn4=(Button) findViewById(R.id.menu4);

        recyclerView = findViewById(R.id.orderRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        orderAdapter = new OrderAdapter(this, orderList);
        recyclerView.setAdapter(orderAdapter);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment1 fragment1 = new Fragment1();
//                fragment1.setOrder(orderList); // order 리스트 전달
                transaction.replace(R.id.frame,fragment1);
                transaction.commit();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment2 fragment2 = new Fragment2();
                transaction.replace(R.id.frame,fragment2);
                transaction.commit();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment3 fragment3 = new Fragment3();
                transaction.replace(R.id.frame,fragment3);
                transaction.commit();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment4 fragment4 = new Fragment4();
                transaction.replace(R.id.frame,fragment4);
                transaction.commit();
            }
        });

    }
    // 주문 목록에 주문을 추가하는 메서드
    public void addOrder(Order order) {
        orderList.add(order);
        orderAdapter.notifyDataSetChanged();
    }
}
