package com.example.hotel.service.user;

import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.RegisterRequestDTO;
import com.example.hotel.dto.request.UserLoginRequestDTO;
import com.example.hotel.dto.response.RegisterResponse;
import com.example.hotel.exception.BizException;

import java.util.List;
import java.util.Map;


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

//    /**
//     * 用户登录
//     *
//     * @param requestDTO
//     * @return
//     */
//    LoginResponse userAuth(LoginRequestDTO requestDTO) throws BizException;
//
//
//
//    /**
//     * 创建用户
//     *
//     * @param insertUserDTO
//     * @return
//     */
//    String insertNewUser(InsertUserDTO insertUserDTO);
//
//    String insertNewUser(String email, String password);
//
//    /**
//     * 修改密码
//     *
//     * @param requestDTO
//     * @return
//     */
//    ResponseResult modifyPassword(PasswordModifyRequestDTO requestDTO) throws BizException;
//
//    /**
//     * 忘记密码
//     *
//     * @param requestDTO
//     * @return
//     * @throws BizException
//     */
//    PasswordResponse forgetPassword(PasswordRequestDTO requestDTO) throws BizException;
//
//    /**
//     * 获取用户基本信息
//     *
//     * @param userId
//     * @return
//     */
//    TUserModel getSimpleUserInfo(String userId, String language) throws BizException;
//
//    /**
//     * 获取用户基本信息
//     */
//    UserBaseDTO getBaseUserInfo(UserInfoRequestDTO requestDTO) throws BizException;
//
//    /**
//     * 获得管理员账户信息
//     * @param requestDTO
//     * @return
//     * @throws BizException
//     */
//    UserBaseDTO getAdminUserInfo(UserInfoRequestDTO requestDTO) throws BizException;
//
//    Map<String, UserBaseDTO> getBaseUserInfoByIds(UserListRequestDTO requestDTO) throws BizException;
//
//    /**
//     * 根据用户id获得管理员信息
//     * @param requestDTO
//     * @return
//     * @throws BizException
//     */
//    Map<String, UserBaseDTO> getAdminUserInfoByIds(UserListRequestDTO requestDTO) throws BizException;
//
//    List<UserBaseInfoDTO> getUserBaseInfoByIds(UserListRequestDTO requestDTO);
//
//    /**
//     * 根据用户Ids获得用户详细信息
//     * @param requestDTO
//     * @return
//     */
//    List<UserDetailDTO> getUserDetailInfoByIds(UserListRequestDTO requestDTO) throws BizException;
//
//    /**
//     * 获取用户详细信息
//     *
//     * @return
//     */
//    UserDetailDTO getDetailUserInfo(UserInfoRequestDTO requestDTO, boolean isLogin) throws BizException;
//
//    UserDetailForUpdateDTO getDetailUserInfoForUpdate(UserInfoRequestDTO requestDTO) throws BizException;
//
//    /**
//     * 更新简介
//     *
//     * @param userIntroductionVO
//     * @return
//     * @throws BizException
//     */
//    boolean updateIntroduction(UserIntroductionVO userIntroductionVO) throws BizException;
//
//    /**
//     * 更新个人中心资料
//     */
//    boolean updateUserInfo(UserInfoUpdateRequestDTO requestDTO, String userId) throws BizException;
//
//    /**
//     * 展示个人或者团队履历
//     *
//     * @param userId
//     */
//    ResumeResponse getResumeInfo(ResumeRequestDTO requestDTO, String userId);
//
//    /**
//     * 修改用户身份
//     *
//     * @param requestDTO
//     * @param userId
//     * @return
//     */
//    boolean changeUserIdentity(UserIdentityChangeRequestDTO requestDTO, String userId) throws BizException;
//
//    /**
//     * 更新用户/团队成员技术方向
//     */
//    void updateTechnologyUserRelation(String userId, String memberId,
//        List<TechnologyDTO> technologyDTOS) throws BizException;
//
//    UserAttributeDTO getUserAttribute(String userId) throws BizException;
//
//    /**
//     * 获取审批人员列表信息
//     *
//     * @param userId
//     * @return
//     * @throws BizException
//     */
//    UserAuditResponseDTO getUserAuditList(String userId) throws BizException;
//
//    /**
//     * 获取当前用户的管理员账户
//     * @param requestDTO
//     * @return
//     */
//    String getAdminUserId(UserInfoRequestDTO requestDTO);
//
//    /**
//     * 构建用户特征
//     */
//    void buildUserProfileList(UserProfileRequestDTO requestDTO);
//
//    /**
//     * 根据用户名右模糊匹配查询
//     * @param requestDTO
//     * @return
//     */
//    Map<String, UserBaseDTO> getBaseUserInfoByName(UserInfoRequestDTO requestDTO);
//
//    /**
//     * 根据邮箱校验用户是否存在
//     * @param email
//     * @return
//     */
//    UserRegisterDTO checkUserByEmail(String email);
}
