package com.example.hotel.feign;

import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.PayBillRequestDTO;
import com.example.hotel.feign.factory.BillFallbackFactory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "Bill", fallbackFactory = BillFallbackFactory.class)
public interface BillFeignClient {

    @PostMapping(value = "/bill/payRoomBill",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseResult<Boolean> payRoomBill(
        @RequestBody PayBillRequestDTO requestDTO );


}
