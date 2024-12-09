package com.example.hotel.service.hotel;

import com.example.hotel.dto.request.AddHotelRequestDTO;
import com.example.hotel.dto.request.ModifyHotelInfoRequestDTO;
import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.response.AddHotelResponse;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.entity.HotelInfo;
import com.example.hotel.exception.BizException;
import org.springframework.data.domain.Page;

public interface HotelService {
    /**
     * addHotel
     *
     * @param requestDTO Request Parameters
     * @return Add results
     */
    AddHotelResponse addHotel(AddHotelRequestDTO requestDTO);

    /**
     * Fuzzy query hotel list
     *
     *
     * @return Query results
     */
    Page<HotelDetailResponse> queryHotelInfo(QueryHotelRequestDTO queryHotelRequestDTO, int page, int size) throws BizException;

    /**
     * Query hotel id
     *
     *
     * @return Query results
     */
    HotelInfo getHotelById(Long id);

    /**
     * Modify hotel information
     *
     *
     * @return results
     */
    void modifyHotelInfo(ModifyHotelInfoRequestDTO requestDTO);

    /**
     * Delete Hotel
     *
     * @return results
     */
    Integer deleteHotel(Long hotelId);
}
