package com.example.hotel.service.impl.hotel;

import com.example.hotel.dto.request.AddHotelRequestDTO;
import com.example.hotel.dto.request.ModifyHotelInfoRequestDTO;
import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.response.AddHotelResponse;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.entity.HotelInfo;
import com.example.hotel.exception.BizException;
import com.example.hotel.mapper.HotelInfoMapper;
import com.example.hotel.repository.HotelInfoRepository;
import com.example.hotel.service.hotel.HotelService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.sql.Timestamp;


/**
 * 酒店服务实现类
 */
@Service
@AllArgsConstructor

public class HotelServiceImpl implements HotelService {

    private final HotelInfoMapper hotelInfoMapper;

    @Override
    @RequestMapping(value = "/hotel/addHotel", method = RequestMethod.POST)
    public AddHotelResponse addHotel(AddHotelRequestDTO requestDTO) {

        // Verify request parameters
        if (requestDTO == null || StringUtils.isBlank(requestDTO.getHotelName())
                || StringUtils.isBlank(requestDTO.getCity())) {
            throw new IllegalArgumentException("Hotel name and city cannot be empty");
        }

        // Constructing hotel entity object
        HotelInfo hotelInfo = new HotelInfo();
        hotelInfo.setHotelName(requestDTO.getHotelName());
        hotelInfo.setAddress(requestDTO.getAddress());
        hotelInfo.setCity(requestDTO.getCity());
        hotelInfo.setPhone(requestDTO.getPhoneNumber());
        hotelInfo.setLevel(requestDTO.getLevel().byteValue());
        hotelInfo.setIsDeleted((byte) 0);  // Not deleted by default
        hotelInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        hotelInfo.setUpdateTime(hotelInfo.getCreateTime());

        // Insert into database
        int rowsInserted = hotelInfoMapper.insert(hotelInfo);
        if (rowsInserted == 0) {
            throw new RuntimeException("Failed to add hotel, affecting 0 rows");
        }

        // Construct return result
        AddHotelResponse response = new AddHotelResponse();
        response.setHotelId(hotelInfo.getId());
        response.setHotelName(hotelInfo.getHotelName());
        response.setMessage("Hotel added successfully");

        return response;
    }

    @Autowired
    private HotelInfoRepository hotelInfoRepository;

    @Override
    public Page<HotelDetailResponse> queryHotelInfo(QueryHotelRequestDTO queryHotelRequestDTO, int page, int size) throws BizException {
        String hotelName = queryHotelRequestDTO.getName();
        String city = queryHotelRequestDTO.getCity();
        String address = queryHotelRequestDTO.getAddress();

        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<HotelInfo> hotelPage = HotelInfoRepository.findByNameContainingAndCityContainingAndAddressContaining(hotelName, city, address, pageable);

        if (hotelPage.isEmpty()) {
            throw new BizException("No matching hotel information found");
        }

        return hotelPage.map(hotel -> {
            HotelDetailResponse response = new HotelDetailResponse();
            response.setHotelId(hotel.getId());
            response.setName(hotel.getHotelName());
            response.setCity(hotel.getCity());
            response.setAddress(hotel.getAddress());
            return response;
        });
    }

    @Override
    public HotelInfo getHotelById(Long id) {
        return hotelInfoMapper.findHotelById(id);
    }

    @Override
    @Transactional
    public void modifyHotelInfo(ModifyHotelInfoRequestDTO requestDTO) {
        HotelInfo hotelInfo = hotelInfoMapper.findHotelById(requestDTO.getHotelId());
        if (hotelInfo == null) {
            throw new IllegalArgumentException("Hotel not found with ID: " + requestDTO.getHotelId());
        }

        // Update Fields
        if (requestDTO.getHotelName() != null) {
            hotelInfo.setHotelName(requestDTO.getHotelName());
        }
        if (requestDTO.getCity() != null) {
            hotelInfo.setCity(requestDTO.getCity());
        }
        if (requestDTO.getAddress() != null) {
            hotelInfo.setAddress(requestDTO.getAddress());
        }
        if (requestDTO.getPhone() != null) {
            hotelInfo.setPhone(requestDTO.getPhone());
        }
        if (requestDTO.getLevel() != null) {
            hotelInfo.setLevel(requestDTO.getLevel().byteValue());
        }

        hotelInfoMapper.updateHotel(hotelInfo);
    }

    @Override
    public Integer deleteHotel(Long hotelId) {
        if (hotelId == null || hotelId <= 0) {
            throw new IllegalArgumentException("Invalid hotel ID");
        }
        int rowsAffected = hotelInfoMapper.deleteHotelById(hotelId);
        if (rowsAffected == 0) {
            throw new IllegalArgumentException("Hotel with ID " + hotelId + " does not exist or is already deleted");
        }
        return rowsAffected;
    }
}
