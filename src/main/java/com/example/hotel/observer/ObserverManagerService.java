package com.example.hotel.observer;

import com.example.hotel.entity.OrderDetail;
import com.example.hotel.entity.User;
import com.example.hotel.entity.UserExample;
import com.example.hotel.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ObserverManagerService {

    private final UserMapper userMapper;

    private static final ObserverManager observerManager = new ObserverManager();

    // Register all users with user_type=1 as observers
    public void addAllType1UsersAsObservers() {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo((byte)0).andUserTypeEqualTo((byte)1);
        List<User> users = userMapper.selectByExample(example);

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
