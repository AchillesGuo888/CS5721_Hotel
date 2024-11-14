package com.example.hotel.service.user;

import com.example.hotel.dto.request.ForgetPasswordRequestDTO;
import com.example.hotel.dto.request.ModifyUserInfoRequestDTO;
import com.example.hotel.dto.request.PasswordModifyRequestDTO;
import com.example.hotel.dto.request.RegisterRequestDTO;
import com.example.hotel.dto.request.UserLoginRequestDTO;
import com.example.hotel.dto.response.RegisterResponse;
import com.example.hotel.dto.response.UpdateInfoResponse;
import com.example.hotel.dto.response.UserInfoResponse;
import com.example.hotel.entity.User;
import com.example.hotel.exception.BizException;
import javax.servlet.http.HttpServletRequest;


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
     * @param httpServletRequest
     * @return
     */
    RegisterResponse userLogin(UserLoginRequestDTO requestDTO,
        HttpServletRequest httpServletRequest) throws BizException;

    /**
     * user logout interface
     * @param token
     * @param httpServletRequest
     */
    void userLogout(String token, HttpServletRequest httpServletRequest);

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

    /**
     * query user info by userId
     * @param userId
     * @return
     * @throws BizException
     */
    User findUserByUserId(String userId) throws BizException;
}
