package com.example.hotel.controller.refund;

import org.springframework.stereotype.Service;

@Service
public class UserPointsService {
    public void deductPoints(String userId, int points) {
        if (points > 0) {
            // 实现数据库操作，回滚积分
            System.out.println("Deducting " + points + " points from user: " + userId);
        } else {
            System.out.println("No points to deduct for user: " + userId);
        }
    }
}