package com.example.mykiosk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mykiosk.model.Order;

public class MainActivity extends AppCompatActivity {
    // View components
    Button btn1, btn2, btn3, btn4;
    Button orderAllCancelBtn;
    Button orderPayCardBtn;
    Button orderPayCouponBtn;
    private RecyclerView recyclerView;
    private TextView totalOrderQuantityTextView;
    private TextView totalOrderPriceTextView;

    // ViewModel
    private OrderViewModel orderViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ViewModel
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment1 fragment1 = new Fragment1();
        transaction.replace(R.id.frame, fragment1);
        transaction.commit();

        btn1 = findViewById(R.id.menu1);
        btn2 = findViewById(R.id.menu2);
        btn3 = findViewById(R.id.menu3);
        btn4 = findViewById(R.id.menu4);
        orderAllCancelBtn = findViewById(R.id.order_all_cancel);
        orderPayCardBtn = findViewById(R.id.order_pay_card_btn);
        orderPayCouponBtn = findViewById(R.id.order_pay_coupon_btn);

        recyclerView = findViewById(R.id.orderRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        totalOrderQuantityTextView = findViewById(R.id.order_total_quantity);
        totalOrderPriceTextView = findViewById(R.id.order_total_price);

        // OrderViewModel 인스턴스 생성
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        // Create and set adapter
        OrderAdapter orderAdapter = new OrderAdapter(this, orderViewModel.getOrderList().getValue(), this);
        orderAdapter.setOrderViewModel(orderViewModel);
        recyclerView.setAdapter(orderAdapter);

        // Observe the order list for changes and update the UI
        orderViewModel.getOrderList().observe(this, orders -> {
            orderAdapter.notifyDataSetChanged();
            totalOrderQuantityTextView.setText(Order.getTotalQuantity(orders));
            totalOrderPriceTextView.setText(Order.getTotalPrice(orders));
        });

        //주문 전체 취소 버튼
        orderAllCancelBtn.setOnClickListener(v -> {
            orderViewModel.removeAllOrders();
        });

        //카드 결제 버튼
        orderPayCardBtn.setOnClickListener(v -> {
            int orderListSize = orderViewModel.getOrderListSize();
            Log.d("orderListSize",String.valueOf(orderListSize));
            if (orderListSize == 0) {
                Log.d("orderListSize","메뉴를 선택해주세요");
                Toast.makeText(this, "메뉴를 선택해주세요", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("테이크아웃 하시겠습니까?");

                builder.setPositiveButton("예", (dialog, which) -> {
                    boolean isTakeout = true;
                    Intent intent = new Intent(MainActivity.this, CardPaymentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("orderList", orderViewModel.getOrderList().getValue());
                    intent.putExtras(bundle);
                    intent.putExtra("totalPrice", Integer.parseInt(Order.getTotalPrice(orderViewModel.getOrderList().getValue())));
                    intent.putExtra("isTakeout", isTakeout);
                    startActivity(intent);
                });

                builder.setNegativeButton("아니요", (dialog, which) -> {
                    boolean isTakeout = false;
                    Intent intent = new Intent(MainActivity.this, CardPaymentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("orderList", orderViewModel.getOrderList().getValue());
                    intent.putExtras(bundle);
                    intent.putExtra("totalPrice", Integer.parseInt(Order.getTotalPrice(orderViewModel.getOrderList().getValue())));
                    intent.putExtra("isTakeout", isTakeout);
                    startActivity(intent);
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        //쿠폰 결제 버튼
        orderPayCouponBtn.setOnClickListener(v -> {
            int orderListSize = orderViewModel.getOrderListSize();
            Log.d("orderListSize",String.valueOf(orderListSize));
            if (orderListSize == 0) {
                Log.d("orderListSize","메뉴를 선택해주세요");
                Toast.makeText(this, "메뉴를 선택해주세요", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("테이크아웃 하시겠습니까?");

                builder.setPositiveButton("예", (dialog, which) -> {
                    boolean isTakeout = true;
                    Intent intent = new Intent(MainActivity.this, CouponPaymentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("orderList", orderViewModel.getOrderList().getValue());
                    intent.putExtras(bundle);
                    intent.putExtra("totalPrice", Integer.parseInt(Order.getTotalPrice(orderViewModel.getOrderList().getValue())));
                    intent.putExtra("isTakeout", isTakeout);
                    startActivity(intent);
                });

                builder.setNegativeButton("아니요", (dialog, which) -> {
                    boolean isTakeout = false;
                    Intent intent = new Intent(MainActivity.this, CouponPaymentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("orderList", orderViewModel.getOrderList().getValue());
                    intent.putExtras(bundle);
                    intent.putExtra("totalPrice", Integer.parseInt(Order.getTotalPrice(orderViewModel.getOrderList().getValue())));
                    intent.putExtra("isTakeout", isTakeout);
                    startActivity(intent);
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btn1.setOnClickListener(v -> {
            FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
            fragment1.setOrderViewModel(orderViewModel);
            transaction1.replace(R.id.frame, fragment1);
            transaction1.commit();
        });

        btn2.setOnClickListener(v -> {
            FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
            Fragment2 fragment2 = new Fragment2();
            fragment2.setOrderViewModel(orderViewModel);
            transaction1.replace(R.id.frame, fragment2);
            transaction1.commit();
        });

        btn3.setOnClickListener(v -> {
            FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
            Fragment3 fragment3 = new Fragment3();
            fragment3.setOrderViewModel(orderViewModel);
            transaction1.replace(R.id.frame, fragment3);
            transaction1.commit();
        });

        btn4.setOnClickListener(v -> {
            FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
            Fragment4 fragment4 = new Fragment4();
            fragment4.setOrderViewModel(orderViewModel);
            transaction1.replace(R.id.frame, fragment4);
            transaction1.commit();
        });
    }

    public OrderViewModel getOrderViewModel() {
        return orderViewModel;
    }
}
