package com.example.mykiosk;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import com.example.mykiosk.model.Coupon;
        import com.example.mykiosk.model.Order;
        import com.example.mykiosk.model.Payment;

        import java.util.ArrayList;

public class CouponPaymentActivity extends AppCompatActivity {
    Button inputCouponBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
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
                Payment payment = new Payment(coupon, totalPrice, isTakeout);

                // 결제할 금액을 사용자에게 입력받는 코드 추가
                EditText paymentAmountEditText = findViewById(R.id.payment_amount_edittext);
                String paymentAmountString = paymentAmountEditText.getText().toString();

                // 입력받은 결제금액을 정수로 변환하여 couponPay.pay() 메소드의 매개변수로 전달
                int paymentAmount = Integer.parseInt(paymentAmountString);


                boolean isPay = payment.pay(paymentAmount); //쿠폰 결제

                TextView paymentResultText = findViewById(R.id.payment_result_text);
                if (isPay) {
                    if(payment.getTotalAmount()>0){
                        Intent intent= new Intent(CouponPaymentActivity.this,CardPaymentActivity.class);
                        intent.putExtra("orderNumber",Payment.orderNumber);
                        intent.putExtra("totalPrice",payment.getTotalAmount());
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("orderList", orderList);
                        intent.putExtras(bundle);
                        Toast.makeText(getApplicationContext(), "쿠폰 결제 완료\n 남은 잔액:"+String.valueOf(payment.getTotalAmount())+"은 카드결제로 진행합니다.", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                    else{
                        paymentResultText.setText(payment.getPaymentResultMsg());
                        Intent intent= new Intent(CouponPaymentActivity.this,OrderFinishActivity.class);
                        intent.putExtra("orderNumber",Payment.orderNumber);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("orderList", orderList);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }


                } else {
                    paymentResultText.setText(payment.getPaymentResultMsg());
                }
            }
        });



    }
}
