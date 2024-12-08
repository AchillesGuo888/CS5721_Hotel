package com.example.hotel.feign.factory;

import com.example.hotel.feign.BillFeignClient;

import com.example.hotel.feign.fallback.BillFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


@Component
public class BillFallbackFactory implements FallbackFactory<BillFeignClient> {

    @Override
    public BillFeignClient create(Throwable throwable) {
        BillFallbackImpl BillFallback = new BillFallbackImpl();
        BillFallback.setCause(throwable);
        return BillFallback;
    }
}
