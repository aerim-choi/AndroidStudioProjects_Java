package com.example.mykiosk;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mykiosk.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Order>> orderListLiveData;

    public OrderViewModel() {
        orderListLiveData = new MutableLiveData<>();
        orderListLiveData.setValue(new ArrayList<>());
    }

    public LiveData<ArrayList<Order>> getOrderList() {
        return orderListLiveData;
    }

    public void addOrder(Order order) {
        ArrayList<Order> currentOrders = orderListLiveData.getValue();
        currentOrders.add(order);
        orderListLiveData.setValue(currentOrders);
    }

    public void removeOrder(Order order) {
        ArrayList<Order> currentOrders = orderListLiveData.getValue();
        currentOrders.remove(order);
        orderListLiveData.setValue(currentOrders);
    }

    public void removeAllOrders() {
        orderListLiveData.setValue(new ArrayList<>());
    }
}
