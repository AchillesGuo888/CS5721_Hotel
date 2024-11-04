package com.example.hotel.service.user;

import com.example.hotel.dto.request.ForgetPasswordRequestDTO;
import com.example.hotel.dto.request.ModifyUserInfoRequestDTO;
import com.example.hotel.dto.request.PasswordModifyRequestDTO;
import com.example.hotel.dto.request.RegisterRequestDTO;
import com.example.hotel.dto.request.UserLoginRequestDTO;
import com.example.hotel.dto.response.RegisterResponse;
import com.example.hotel.dto.response.UpdateInfoResponse;
import com.example.hotel.dto.response.UserInfoResponse;
import com.example.hotel.exception.BizException;


public interface UserService {

    /**
     * user sign up
     *
     * @param requestDTO
     * @return
     */
    RegisterResponse userSignUp(RegisterRequestDTO requestDTO) throws BizException;

    /**
     * user login interface
     * @param requestDTO
     * @return
     */
    RegisterResponse userLogin(UserLoginRequestDTO requestDTO) throws BizException;

    /**
     * user logout interface
     * @param token
     */
    void userLogout(String token);

    /**
     * change password
     *
     * @param requestDTO
     * @return
     */
    UpdateInfoResponse modifyPassword(PasswordModifyRequestDTO requestDTO) throws BizException;

    /**
     * forget password
     *
     * @param requestDTO
     * @return
     * @throws BizException
     */
    UpdateInfoResponse forgetPassword(ForgetPasswordRequestDTO requestDTO) throws BizException;

    /**
     * get user info
     */
    UserInfoResponse getUserInfo(String token) throws BizException;

    /**
     * update user info
     */
    UpdateInfoResponse updateUserInfo(ModifyUserInfoRequestDTO requestDTO, String token) throws BizException;

}
