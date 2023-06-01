package com.example.mykiosk;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mykiosk.model.Menu;
import com.example.mykiosk.model.Order;
import com.example.mykiosk.model.Side;

import java.util.ArrayList;

public class Fragment3 extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    ArrayList<Side> SideList = new ArrayList<>();
    public Fragment3(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment3,container,false);
        recyclerView=(RecyclerView) rootView.findViewById(R.id.recyclerview);
        SideList.add(new Side("감자튀김M",R.drawable.potato,"3000"));
        SideList.add(new Side("감자튀김L",R.drawable.potato,"3500"));
        SideList.add(new Side("치즈스틱",R.drawable.cheesestick,"3400"));
        SideList.add(new Side("어니언링",R.drawable.onionring,"3300"));
        recyclerView.setHasFixedSize(true);

        adapter=new MyAdapter(getActivity(),SideList,new MyAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Menu menu) {
                showQuantityDialog(menu);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


        return rootView;
    }
    private void showQuantityDialog(final Menu menu) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("메뉴 추가");
        builder.setMessage(menu.getMenuName() + "의 수량을 변경하세요");

        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.quantity_dialog, null);
        final TextView quantityTextView = dialogView.findViewById(R.id.quantity_textview);
        Button increaseButton = dialogView.findViewById(R.id.increase_button);
        Button decreaseButton = dialogView.findViewById(R.id.decrease_button);

        builder.setView(dialogView);

        final int[] quantity = {1}; // 초기 수량은 1로 설정

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity[0]++;
                quantityTextView.setText(String.valueOf(quantity[0]));
            }
        });

        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity[0] > 1) {
                    quantity[0]--;
                    quantityTextView.setText(String.valueOf(quantity[0]));
                }
            }
        });

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 주문목록에 새로운 메뉴 추가
                Order orderMenu = new Order(quantity[0], menu.getMenuName(), menu.getMenuPrice());
                ((MainActivity) getActivity()).addOrder(orderMenu);
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
