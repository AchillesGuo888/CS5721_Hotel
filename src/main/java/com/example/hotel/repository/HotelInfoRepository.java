package com.example.hotel.repository;

import com.example.hotel.entity.HotelInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelInfoRepository extends JpaRepository<HotelInfo, Long> {

    // Perform fuzzy query based on hotel name, city and address, and perform paging
    Page<HotelInfo> findByHotelNameContainingAndCityContainingAndAddressContaining(
            String hotelName, String city, String address, Pageable pageable);

}
