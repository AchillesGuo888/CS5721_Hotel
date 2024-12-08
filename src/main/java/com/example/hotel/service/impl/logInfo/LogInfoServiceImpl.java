package com.example.hotel.service.impl.logInfo;

import com.example.hotel.entity.LogInfo;
import com.example.hotel.mapper.LogInfoMapper;
import com.example.hotel.service.logInfo.LogInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class LogInfoServiceImpl implements LogInfoService {

  private final LogInfoMapper logInfoMapper;

  @Override
  public void save(LogInfo log) {
    logInfoMapper.insertSelective(log);
  }
}
