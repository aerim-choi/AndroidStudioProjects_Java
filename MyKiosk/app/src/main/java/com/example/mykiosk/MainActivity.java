package com.example.mykiosk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mykiosk.model.Card;
import com.example.mykiosk.model.Coupon;
import com.example.mykiosk.model.Menu;
import com.example.mykiosk.model.Order;
import com.example.mykiosk.model.Payment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4;
    Button orderAllCancelBtn;
    Button orderPayCardBtn;
    Button orderPayCouponBtn;

    private ArrayList<Order> orderList = new ArrayList<Order>();
    private RecyclerView recyclerView;
    public OrderAdapter orderAdapter;

    private TextView totalOrderQuantityTextView;
    private TextView totalOrderPriceTextView;

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
        orderAllCancelBtn=(Button)findViewById(R.id.order_all_cancel) ;
        orderPayCardBtn=(Button)findViewById(R.id.order_pay_card_btn);
        orderPayCouponBtn=(Button)findViewById(R.id.order_pay_coupon_btn);

        recyclerView = findViewById(R.id.orderRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        totalOrderQuantityTextView=findViewById(R.id.order_total_quantity);
        totalOrderPriceTextView=findViewById(R.id.order_total_price);


        orderAdapter = new OrderAdapter(this, orderList,this);
        recyclerView.setAdapter(orderAdapter);

        //주문 전체 취소 버튼
        orderAllCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allRemoveOrder();
            }
        });
        //카드 결제 버튼
        orderPayCardBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("테이크아웃 하시겠습니까?");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isTakeout = true;
                        Intent intent= new Intent(MainActivity.this,CardPaymentActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("orderList", orderList);
                        intent.putExtras(bundle);
                        intent.putExtra("totalPrice",Integer.parseInt(Order.getTotalPrice(orderList)));
                        intent.putExtra("isTakeout",isTakeout);

                        startActivity(intent);

                    }
                });

                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isTakeout = false;
                        Intent intent= new Intent(MainActivity.this,CardPaymentActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("orderList", orderList);
                        intent.putExtras(bundle);
                        intent.putExtra("totalPrice",Integer.parseInt(Order.getTotalPrice(orderList)));
                        intent.putExtra("isTakeout",isTakeout);
                        startActivity(intent);

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        //쿠폰 결제 버튼
        orderPayCouponBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("테이크아웃 하시겠습니까?");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isTakeout = true;
                        Intent intent= new Intent(MainActivity.this,CouponPaymentActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("orderList", orderList);
                        intent.putExtras(bundle);
                        intent.putExtra("totalPrice",Integer.parseInt(Order.getTotalPrice(orderList)));
                        intent.putExtra("isTakeout",isTakeout);

                        startActivity(intent);

                    }
                });

                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isTakeout = false;
                        Intent intent= new Intent(MainActivity.this,CouponPaymentActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("orderList", orderList);
                        intent.putExtras(bundle);
                        intent.putExtra("totalPrice",Integer.parseInt(Order.getTotalPrice(orderList)));
                        intent.putExtra("isTakeout",isTakeout);

                        startActivity(intent);
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

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
        totalOrderQuantityTextView.setText(Order.getTotalQuantity(orderList));
        totalOrderPriceTextView.setText(Order.getTotalPrice(orderList));
    }
    public void removeOrder(Order order) {
        orderList.remove(order);
        orderAdapter.notifyDataSetChanged();
        totalOrderQuantityTextView.setText(Order.getTotalQuantity(orderList));
        totalOrderPriceTextView.setText(Order.getTotalPrice(orderList));
    }
    public void allRemoveOrder(){
        orderList.removeAll(orderList);
        orderAdapter.notifyDataSetChanged();
        totalOrderQuantityTextView.setText(Order.getTotalQuantity(orderList));
        totalOrderPriceTextView.setText(Order.getTotalPrice(orderList));
    }
}
