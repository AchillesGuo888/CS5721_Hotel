package com.example.hotel.service.hotel;

import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.AddHotelRequestDTO;
import com.example.hotel.dto.request.DeleteHotelInfoRequestDTO;
import com.example.hotel.dto.request.ModifyHotelInfoRequestDTO;
import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.entity.Hotel;
import com.example.hotel.mapper.HotelMapper;
import com.example.hotel.mapper.OrderMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private OrderMapper orderMapper;

    public ResponseResult addHotel(AddHotelRequestDTO addHotelRequestDTO) {
        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(addHotelRequestDTO, hotel);
        int rowsAffected = hotelMapper.addHotel(hotel);
        return rowsAffected > 0 ? ResponseResult.ofSuccess(hotel)
                : ResponseResult.ofError(500L, "Failed to add hotel");
    }

    public ResponseResult<HotelDetailResponse> queryHotelInfo(QueryHotelRequestDTO queryHotelRequestDTO) {
        Hotel hotel = hotelMapper.findHotelById(queryHotelRequestDTO.getHotelId());
        if (hotel == null) {
            return ResponseResult.ofError(404L, "Hotel not found");
        }
        //Construct HotelDetailResponse response object
        HotelDetailResponse response = HotelDetailResponse.builder()
                .name(hotel.getName())
                .address(hotel.getAddress())
                .city(hotel.getCity())
                .state(hotel.getState())
                .country(hotel.getCountry())
                .zipCode(hotel.getZipCode())
                .facilities(hotel.getFacilities())
                .totalRooms(hotel.getTotalRooms())
                .phoneNumber(hotel.getPhoneNumber())
                .build();
        //Returns the encapsulated response result
        return ResponseResult.ofSuccess(response);
    }

    public ResponseResult modifyHotelInfo(ModifyHotelInfoRequestDTO modifyHotelInfoRequestDTO) {
        Long hotelId = modifyHotelInfoRequestDTO.getHotelId();
        Hotel hotel = hotelMapper.findHotelById(hotelId);
        if (hotel == null) {
            return ResponseResult.ofError(404L, "Hotel not found");
        }
        BeanUtils.copyProperties(modifyHotelInfoRequestDTO, hotel);
        hotelMapper.modifyHotel(hotel);
        return ResponseResult.ofSuccess();
    }

    public ResponseResult deleteHotel(DeleteHotelInfoRequestDTO deleteHotelInfoRequestDTO) {
        Long hotelId = deleteHotelInfoRequestDTO.getHotelId();
        if (orderMapper.findOrdersByHotelId(hotelId) > 0) {
            return ResponseResult.ofError(403L, "Cannot delete hotel with existing orders");
        }
        int deletedRows = hotelMapper.deleteHotelById(hotelId);
        return deletedRows > 0 ? ResponseResult.ofSuccess()
                : ResponseResult.ofError(404L, "Hotel not found or could not be deleted");
    }

    public ResponseResult<List<AvailableHotelResponse>> queryHotelList(QueryHotelRequestDTO queryHotelRequestDTO) {
        //Calculating paging offsets
        int offset = (queryHotelRequestDTO.getPage() - 1) * queryHotelRequestDTO.getSize();
        //Call the database query operation to get the Hotel list
        List<Hotel> hotels = hotelMapper.findHotelsByConditions(
                queryHotelRequestDTO.getName(),
                queryHotelRequestDTO.getAddress(),
                queryHotelRequestDTO.getCity(),
                queryHotelRequestDTO.getState(),
                queryHotelRequestDTO.getCountry(),
                queryHotelRequestDTO.getZipCode(),
                offset,
                queryHotelRequestDTO.getSize()
        );
        //If there is no data, an error response is returned
        if (hotels.isEmpty()) {
            return ResponseResult.ofError(404L, "No hotels found matching the given criteria");
        }
        //Convert the Hotel entity to an AvailableHotelResponse response object
        List<AvailableHotelResponse> responses = hotels.stream()
                .map(hotel -> {
                    //Creating a Response Object
                    AvailableHotelResponse response = new AvailableHotelResponse();
                    //Use BeanUtils to copy the Hotel properties into the response object
                    BeanUtils.copyProperties(hotel, response);
                    return response;//Returns the constructed response object
                }).collect(Collectors.toList());//Collect stream objects into a List
        //Returns the encapsulated response result
        return ResponseResult.ofSuccess(responses);
    }
}
