package com.example.hotel.schedule;

import com.example.hotel.service.order.OrderInfoService;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@AllArgsConstructor
public class ScheduleTask {

  private final OrderInfoService orderInfoService;

  // Orders for the end of the day are processed at 12 noon each day
  @Scheduled(cron = "0 0 12 * * ?")
  public void finishOrderTask() {
    orderInfoService.scheduleFinishOrder();
    System.out.println("Schedule task for:" + LocalDate.now());
  }
}
