package com.example.mykiosk;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;

        import com.example.mykiosk.model.Coupon;
        import com.example.mykiosk.model.Order;
        import com.example.mykiosk.model.Payment;

        import java.util.ArrayList;

public class CouponPaymentActivity extends AppCompatActivity {
    Button inputCouponBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_payment);

        Intent intent = getIntent();
        int totalPrice = intent.getIntExtra("totalPrice", 0);
        boolean isTakeout=intent.getBooleanExtra("isTakeout",false);
        Bundle receivedBundle = intent.getExtras();
        ArrayList<Order> orderList = (ArrayList<Order>) receivedBundle.getSerializable("orderList");

        inputCouponBtn=(Button)findViewById(R.id.input_coupon_btn);


        inputCouponBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Coupon coupon = new Coupon(12345678, 30000);
                Payment couponPay = new Payment(coupon, totalPrice, isTakeout);

                boolean isPay = couponPay.pay(); //쿠폰 결제

                TextView paymentResultText = findViewById(R.id.payment_result_text);
                if (isPay) {
                    paymentResultText.setText(couponPay.displayPrompt());
                    Intent intent= new Intent(CouponPaymentActivity.this,OrderFinishActivity.class);
                    intent.putExtra("orderNumber",Payment.orderNumber);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("orderList", orderList);
                    intent.putExtras(bundle);
                    startActivity(intent);

                } else {
                    paymentResultText.setText(couponPay.displayPrompt());
                }
            }
        });



    }
}
