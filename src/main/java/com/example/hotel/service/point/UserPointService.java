package com.example.hotel.service.point;


import com.example.hotel.entity.OrderBase;
import java.util.List;


public interface UserPointService {


  void batchInsertUserPoint(List<OrderBase> orderBaseList);

  void updateUserLevel(List<String> userIds);
}
