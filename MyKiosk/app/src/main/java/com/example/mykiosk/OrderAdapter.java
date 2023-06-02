package com.example.mykiosk;
// OrderAdapter.java

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mykiosk.model.Order;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private Context context;
    private ArrayList<Order> orderList;
    private MainActivity mainActivity;
    // OrderViewModel 인스턴스 변수 선언
    private OrderViewModel orderViewModel;

    public OrderAdapter(Context context, ArrayList<Order> orderList, MainActivity mainActivity) {
        this.context = context;
        this.orderList = orderList;
        this.mainActivity = mainActivity;
    }
    public void setOrderViewModel(OrderViewModel orderViewModel) {
        this.orderViewModel = orderViewModel;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Order order = orderList.get(position);

        holder.quantityTextView.setText(String.valueOf(order.getOrderQuantity()));
        holder.menuTextView.setText(order.getOrderMenu());
        holder.priceTextView.setText(order.getUnitAmount());

        holder.orderCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 주문 취소 버튼 클릭 시 해당 주문을 삭제합니다.
                orderViewModel.removeOrder(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView quantityTextView;
        TextView menuTextView;
        TextView priceTextView;

        TextView totalQuantityTextView;

        TextView totalPriceTextView;
        ImageButton orderCancelBtn;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            quantityTextView = itemView.findViewById(R.id.order_menu_quentity);
            menuTextView = itemView.findViewById(R.id.order_menu_name);
            priceTextView = itemView.findViewById(R.id.order_menu_price);
            totalQuantityTextView=itemView.findViewById(R.id.order_total_price);
            totalPriceTextView=itemView.findViewById(R.id.order_total_price);
            orderCancelBtn=itemView.findViewById(R.id.order_cancel_btn);

        }
    }
}

