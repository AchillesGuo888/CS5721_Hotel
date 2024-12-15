package com.example.hotel.service.hotel;

import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.entity.HotelInfo;
import com.example.hotel.exception.BizException;
import com.example.hotel.mapper.HotelInfoMapper;
import com.example.hotel.repository.HotelInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotelInfoService {

    private HotelInfoRepository hotelInfoRepository;

    /**
     * Perform fuzzy query based on query conditions
     */
    public Page<HotelDetailResponse> queryHotelInfo(QueryHotelRequestDTO queryHotelRequestDTO, int page, int size) throws BizException {
        Pageable pageable = PageRequest.of(page, size);

        // Fuzzy query is guaranteed not to be null
        String name = queryHotelRequestDTO.getName() != null ? queryHotelRequestDTO.getName() : "";
        String city = queryHotelRequestDTO.getCity() != null ? queryHotelRequestDTO.getCity() : "";
        String address = queryHotelRequestDTO.getAddress() != null ? queryHotelRequestDTO.getAddress() : "";

        // Fuzzy query through HotelInfoRepository
        Page<HotelInfo> hotels = hotelInfoRepository.findByHotelNameContainingAndCityContainingAndAddressContaining(
                name, city, address, pageable);

        if (hotels.isEmpty()) {
            throw new BizException("No matching hotel information found");
        }

        // Convert the query result to HotelDetailResponse
        return hotels.map(hotel -> new HotelDetailResponse(
                hotel.getId(), hotel.getHotelName(), hotel.getAddress(), hotel.getCity()
        ));
    }

    /**
     * Query by ID
     */

    private HotelInfoMapper hotelInfoMapper;

    public HotelInfo getHotelById(Long id) {
        return hotelInfoMapper.findHotelById(id);
    }
}
