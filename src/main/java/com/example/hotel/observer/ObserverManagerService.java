package com.example.hotel.observer;

import com.example.hotel.entity.OrderDetail;
import com.example.hotel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObserverManagerService {
    @Autowired
    private UserRepository userRepository;

    private final ObserverManager observerManager = new ObserverManager();

    // Register all users with user_type=1 as observers
    public void addAllType1UsersAsObservers() {
        List<User> users = userRepository.findAllByUserType(1);

        for (User user : users) {
            UserObserver observer = new UserObserver(user.getId());
            observerManager.addObserver(observer);
        }
        System.out.println("ObserverManager now has " + observerManager.getObserverCount() + " observers.");
    }

    // Notify all observers
    public void notifyAllObservers(OrderDetail orderDetail) {
        observerManager.notifyObservers(orderDetail);
    }

}
