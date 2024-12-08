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
     * 添加酒店
     *
     * @param requestDTO 请求参数
     * @return 添加结果
     */
    AddHotelResponse addHotel(AddHotelRequestDTO requestDTO);

    /**
     * 模糊查询酒店列表
     *
     *
     * @return 查询结果
     */
    Page<HotelDetailResponse> queryHotelInfo(QueryHotelRequestDTO queryHotelRequestDTO, int page, int size) throws BizException;

    /**
     * 查询酒店id
     *
     *
     * @return 查询结果
     */
    HotelInfo getHotelById(Long id);

    /**
     * 修改酒店信息
     *
     *
     * @return 结果
     */
    void modifyHotelInfo(ModifyHotelInfoRequestDTO requestDTO);

    /**
     * 删除酒店
     *
     * @return 结果
     */
    Integer deleteHotel(Long hotelId);
}
