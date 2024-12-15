package com.example.hotel.service.logInfo;

import com.example.hotel.entity.LogInfo;
import org.springframework.stereotype.Component;

@Component
public interface LogInfoService {
  void save(LogInfo log);

}
