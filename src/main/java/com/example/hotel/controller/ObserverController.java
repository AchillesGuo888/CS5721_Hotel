package com.example.hotel.controller;

import com.example.hotel.entity.OrderDetail;
import com.example.hotel.observer.ObserverManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/observer")
public class ObserverController {
    @Autowired
    private ObserverManagerService observerManagerService;

    @PostMapping("/add-type1-users")
    public String addType1UsersAsObservers() {
        observerManagerService.addAllType1UsersAsObservers();
        return "Type 1 users added as observers successfully!";
    }

    @PostMapping("/notify")
    public String notifyObservers(@RequestBody OrderDetail orderDetail) {
        observerManagerService.notifyAllObservers(orderDetail);
        return "Notification sent to all observers!";
    }
}
