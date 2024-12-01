package com.example.hotel.controller;


import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.PayBillRequestDTO;
import com.example.hotel.dto.request.PayDifferenceRequestDTO;
import com.example.hotel.exception.BizException;
import com.example.hotel.service.bill.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/bill")
@Api(tags = "Bill API")
@AllArgsConstructor
public class BillController {

  private final BillService billService;

  /**
   * pay room bill
   *
   * @return
   */
  @PostMapping("/payRoomBill")
  @RequestMapping(value = "payRoomBill", method = RequestMethod.POST)
  public ResponseResult payRoomBill(@RequestHeader("Authorization") String token,
      @ApiParam(value = "bill detail", required = true) @RequestBody PayBillRequestDTO requestDTO) {
    try {
      billService.payBill(token, requestDTO);
    } catch (BizException e) {
      log.error("user login error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }
    return ResponseResult.ofSuccess();
  }

  /**
   * pay update room difference
   *
   * @return
   */
  @PostMapping("/payRoomDifference")
  @RequestMapping(value = "payRoomDifference", method = RequestMethod.POST)
  public ResponseResult payRoomDifference(@RequestHeader("Authorization") String token,
      @ApiParam(value = "difference bill detail", required = true) @RequestBody PayDifferenceRequestDTO requestDTO) {
    return ResponseResult.ofSuccess();
  }


}
