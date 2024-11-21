package com.example.hotel.controller.refund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserPointsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void deductPoints(String userId, int points) {
        if (points > 0) {
            // Call database operations to update user points
            System.out.println("Deducting " + points + " points from user: " + userId);

            //Construct SQL queries to update the points in the database
            String sql = "UPDATE user_points SET points = points - ? WHERE user_id = ?";
            //Execute database operations and update points
            int rowsAffected = jdbcTemplate.update(sql, points, userId);

            //If the number of updated records is 0, it means that the user has not been found or has insufficient points.
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("User not found or insufficient points to deduct.");
            }

            //Output the log of successful operation
            System.out.println("Successfully deducted " + points + " points from user: " + userId);
        } else {
            //Handle invalid integral values and print no operation logs
            System.out.println("No points to deduct for user: " + userId);
        }
    }
}