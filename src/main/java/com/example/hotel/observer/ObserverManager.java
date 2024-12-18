package com.example.hotel.observer;

import com.example.hotel.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObserverManager implements Observable {
    private final List<Observer> observers = new ArrayList<>();

    @Autowired
    private AdminService adminService;

    @PostConstruct
    public void init() {
        // Add observers at initialization time
        observers.add(adminService);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(OrderDetail orderDetail) {
        for (Observer observer : observers) {
            observer.update(orderDetail);
        }
    }

    public int getObserverCount() {
        return observers.size();
    }
}