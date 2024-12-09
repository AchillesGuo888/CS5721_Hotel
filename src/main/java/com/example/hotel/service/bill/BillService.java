package com.example.hotel.service.bill;

import com.example.hotel.dto.request.ForgetPasswordRequestDTO;
import com.example.hotel.dto.request.ModifyUserInfoRequestDTO;
import com.example.hotel.dto.request.PasswordModifyRequestDTO;
import com.example.hotel.dto.request.PayBillRequestDTO;
import com.example.hotel.dto.request.RegisterRequestDTO;
import com.example.hotel.dto.request.UserLoginRequestDTO;
import com.example.hotel.dto.response.RegisterResponse;
import com.example.hotel.dto.response.UpdateInfoResponse;
import com.example.hotel.dto.response.UserInfoResponse;
import com.example.hotel.entity.User;
import com.example.hotel.exception.BizException;
import javax.servlet.http.HttpServletRequest;


public interface BillService {

    /**
     * Pay bill
     * @param token
     * @param requestDTO
     * @throws BizException
     */
    void payBill(String token,PayBillRequestDTO requestDTO) throws BizException;

}
