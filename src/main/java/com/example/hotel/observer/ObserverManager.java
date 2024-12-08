package com.example.hotel.observer;

import com.example.hotel.entity.OrderDetail;
import java.util.ArrayList;
import java.util.List;

public class ObserverManager {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(OrderDetail orderDetail) {
        for (Observer observer : observers) {
            observer.update(orderDetail);
        }
    }
}
