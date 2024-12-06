package com.example.hotel.service.hotel;

import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.entity.HotelInfo;
import com.example.hotel.exception.BizException;
import com.example.hotel.mapper.HotelInfoMapper;
import com.example.hotel.repository.HotelInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelInfoService {

    @Autowired
    private HotelInfoRepository hotelInfoRepository;

    /**
     * 根据查询条件进行模糊查询
     */
    public static Page<HotelDetailResponse> queryHotelInfo(QueryHotelRequestDTO queryHotelRequestDTO, int page, int size) throws BizException {
        Pageable pageable = PageRequest.of(page, size);

        // 模糊查询条件
        String name = queryHotelRequestDTO.getName() != null ? queryHotelRequestDTO.getName() : "";
        String city = queryHotelRequestDTO.getCity() != null ? queryHotelRequestDTO.getCity() : "";
        String address = queryHotelRequestDTO.getAddress() != null ? queryHotelRequestDTO.getAddress() : "";

        // 通过HotelInfoRepository进行模糊查询
        Page<HotelInfo> hotels = HotelInfoRepository.findByNameContainingAndCityContainingAndAddressContaining(
                name, city, address, pageable);

        if (hotels.isEmpty()) {
            throw new BizException("未找到符合条件的酒店信息");
        }

        // 将查询结果转换为HotelDetailResponse
        return hotels.map(hotel -> new HotelDetailResponse(
                hotel.getId(), hotel.getHotelName(), hotel.getAddress(), hotel.getCity()
        ));
    }

    /**
     * 根据ID查询进行查询
     */
    @Autowired
    private HotelInfoMapper hotelInfoMapper;

    public HotelInfo getHotelById(Long id) {
        return hotelInfoMapper.findHotelById(id);
    }
}
