package com.example.hotel.repository;

import com.example.hotel.entity.HotelInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelInfoRepository extends JpaRepository<HotelInfo, Long> {

    // 根据酒店名称、城市和地址进行模糊查询，并进行分页
    static Page<HotelInfo> findByNameContainingAndCityContainingAndAddressContaining(
            String hotelName, String city, String address, Pageable pageable) {
        return null;
    }
}
