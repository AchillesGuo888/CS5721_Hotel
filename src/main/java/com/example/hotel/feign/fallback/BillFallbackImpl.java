package com.example.hotel.feign.fallback;

import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.PayBillRequestDTO;
import com.example.hotel.feign.BillFeignClient;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@Component
public class BillFallbackImpl implements BillFeignClient {

    @Setter
    private Throwable cause;


    @Override
    public ResponseResult<Boolean> payRoomBill(@RequestBody PayBillRequestDTO requestDTO) {
        log.error("payRoomBill error:{}",cause.getMessage());
        return null;
    }
}
