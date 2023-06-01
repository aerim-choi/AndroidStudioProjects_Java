package com.example.mykiosk;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;

        import com.example.mykiosk.model.Coupon;
        import com.example.mykiosk.model.Payment;

public class CouponPaymentActivity extends AppCompatActivity {
    Button inputCouponBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_payment);

        Intent intent = getIntent();
        int totalPrice = intent.getIntExtra("totalPrice", 0);
        boolean isTakeout=intent.getBooleanExtra("isTakeout",false);
        inputCouponBtn=(Button)findViewById(R.id.input_coupon_btn);


        inputCouponBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Coupon coupon = new Coupon(12345678, 30000);
                Payment couponPay = new Payment(coupon, totalPrice, isTakeout);

                String isPay = couponPay.pay(); //쿠폰 결제
                Log.d("isPay", isPay);
                TextView paymentResultText = findViewById(R.id.payment_result_text);
                if (isPay.equals("결제 성공")) {
                    paymentResultText.setText("결제가 완료되었습니다.");
                    Intent intent= new Intent(CouponPaymentActivity.this,OrderFinishActivity.class);
                    intent.putExtra("orderNumber",Payment.orderNumber);

                    startActivity(intent);

                } else {
                    paymentResultText.setText("결제에 실패하였습니다.");
                }
            }
        });



    }
}
