package com.example.mykiosk;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mykiosk.model.Menu;
import com.example.mykiosk.model.Order;
import com.example.mykiosk.model.TimeSale;

import java.sql.Time;
import java.util.ArrayList;

public class Fragment4 extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    ArrayList<TimeSale> TimeSaleList = new ArrayList<>();
    public Fragment4(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment4,container,false);
        recyclerView=(RecyclerView) rootView.findViewById(R.id.recyclerview);
        TimeSaleList.add(new TimeSale("와퍼주니어(행사)",R.drawable.hamburger1,"1900"));
        TimeSaleList.add(new TimeSale("불고기와퍼주니어(행사)",R.drawable.hamburger1,"1900"));
        TimeSaleList.add(new TimeSale("치즈와퍼주니어(행사)",R.drawable.hamburger1,"2200"));
        TimeSaleList.add(new TimeSale("할라피뇨와퍼주니어(행사)",R.drawable.hamburger1,"2300"));
        recyclerView.setHasFixedSize(true);

        adapter=new MyAdapter(getActivity(),TimeSaleList,new MyAdapter.OnItemClickListener(){
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

        final EditText quantityEditText = new EditText(getActivity());
        quantityEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(quantityEditText);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String quantityStr = quantityEditText.getText().toString();
                int quantity = Integer.parseInt(quantityStr);

                // 주문목록에 새로운 메뉴 추가
                Order orderMenu = new Order(quantity, menu.getMenuName(), menu.getMenuPrice());
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