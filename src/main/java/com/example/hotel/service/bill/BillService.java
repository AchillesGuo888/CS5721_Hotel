package com.example.hotel.service.bill;

import com.example.hotel.dto.request.PayBillRequestDTO;
import com.example.hotel.exception.BizException;


public interface BillService {

  /**
   * Pay bill
   *
   * @param token
   * @param requestDTO
   * @throws BizException
   */
  void payBill(String token, PayBillRequestDTO requestDTO) throws BizException;

}
