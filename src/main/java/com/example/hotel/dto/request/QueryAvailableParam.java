package com.example.hotel.dto.request;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class QueryAvailableParam {
  List<Long> hotelIds;
  LocalDate startDate;
  LocalDate endDate;
  Integer quantity;
  List<Long> roomTypeIds;
}
