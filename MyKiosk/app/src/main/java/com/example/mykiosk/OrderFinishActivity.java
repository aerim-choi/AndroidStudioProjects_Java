package com.example.mykiosk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mykiosk.model.Order;
import com.example.mykiosk.model.ReceiptPrinter;

import java.util.ArrayList;

public class OrderFinishActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_finish);

        Intent intent = getIntent();
        int orderNumber = intent.getIntExtra("orderNumber", 1);
        Bundle receivedBundle = intent.getExtras();
        ArrayList<Order> orderList = (ArrayList<Order>) receivedBundle.getSerializable("orderList");

        TextView orderNumberTextView = findViewById(R.id.ordernumber_textView);
        orderNumberTextView.setText(String.valueOf(orderNumber));

        ReceiptPrinter receiptPrinter = new ReceiptPrinter(Order.getTotalPrice(orderList), orderList, orderNumber);

        // 영수증 출력
        Log.d("영수증:", receiptPrinter.printReceipt());

        OrderViewModel orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.removeAllOrders();
    }
}
