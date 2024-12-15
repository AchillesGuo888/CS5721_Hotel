package com.example.hotel.controller;


import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.common.RoomTypePriceDTO;
import com.example.hotel.service.roomType.RoomTypePriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/roomTypePrice")
@Api(tags = "Room Type Price API")
@AllArgsConstructor
public class RoomTypePriceController {
    private final RoomTypePriceService roomTypePriceService;

    /**
     * add room type price
     */
    @PostMapping("/add")
    public ResponseResult<String> addRoomType(
            @ApiParam(value = "Room type price details", required = true)
            @RequestBody RoomTypePriceDTO requestDTO) {
        return ResponseResult.ofSuccess(roomTypePriceService.addRoomTypePrice(requestDTO));
    }

    /**
     * query room type price
     * @return
     */
    @GetMapping("/query")
    public ResponseResult<RoomTypePriceDTO> queryRoomTypePrice(
            @ApiParam(value = "query room type price details ", required = true)
            @RequestParam Long roomTypeId) {
        return ResponseResult.ofSuccess(roomTypePriceService.getRoomTypePrice(roomTypeId));
    }

    /**
     * modify room type info
     * @return
     */
    @PutMapping("/modify")
    public ResponseResult<RoomTypePriceDTO> modifyRoomTypePrice(@ApiParam(value = "room type price details", required = true)
                                                                @RequestBody RoomTypePriceDTO requestDTO) {
        return ResponseResult.ofSuccess(roomTypePriceService.modifyRoomTypePrice(requestDTO));
    }

    /**
     * delete room type info
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseResult<HttpStatus> deleteRoomTypePrice(@ApiParam(value = "delete room type price", required = true)
                                                          @RequestParam Long roomTypeId) {
        roomTypePriceService.deleteRoomTypePrice(roomTypeId);
        return ResponseResult.ofSuccess(HttpStatus.OK);
    }
}
