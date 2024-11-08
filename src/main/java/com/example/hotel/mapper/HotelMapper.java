package com.example.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hotel.entity.Hotel;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface HotelMapper extends BaseMapper<Hotel> {
    /**
     * add hotel
     *
     * @param hotel information
     * @return Number of records inserted
     */
    int addHotel(Hotel hotel);

    /**
     * delete hotel by id
     *
     * @param hotelId
     * @return Number of records deleted
     */
    int deleteHotelById(@Param("hotelId") Long hotelId);

    /**
     * modify hotel information
     *
     * @param hotel updated information
     * @return Number of records updated
     */
    int modifyHotel(Hotel hotel);

    /**
     * find hotel by id
     * @param hotelId
     * @return hotel
     */
    Hotel findHotelById(@Param("hotelId") Long hotelId);

    /**
     * search for hotel lists based on geographic location
     *
     * @param city hotel city
     * @param state hotel state
     * @param country hotel country
     * @return list of eligible hotels
     */
    List<Hotel> findHotelsByLocation(@Param("city") String city,
                                     @Param("state") String state,
                                     @Param("country") String country);
}
