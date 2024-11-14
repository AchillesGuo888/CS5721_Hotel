package com.example.hotel.common.base;

import com.example.hotel.enums.BaseEnum;

/**
 * @Author qzb
 */
public enum ResponseCode implements BaseEnum<Long> {

    /**
     *
     */
    server_err("server error", "", 500L),
    forbidden("Wrong Operation","",403L),
    success("Successful", "", 200L),
    token_error("Token error", "", 300L),
    email_error_rules("Please enter the correct email address.","", 1011L),
    code_false("Please enter the correct validation code", "", 1007L),
    param_error("Parameter Error", "", 1001L),
    password_error_rules("Password format error", "", 1005L),
    email_has_exist("The email has existed", "", 1010L),
    email_password_not_match("The email or password is incorrect", "", 1003L),
    email_not_exist("The email does not exist", "", 1006L),
    password_not_same("Two passwords are inconsistent.", "", 1004L),
    password_old_new_same("The old and new password can be same", "", 1012L),
    password_old_error_rules("Password format error", "", 1013L),
    password_new_error_rules("Password format error", "", 1014L),
    password_old_not_correct("The old password is wrong", "", 1015L),

    room_count_error("There is no more rooms for all guests", "", 2001L),
    room_type_not_exists("Can't find this room type", "", 2002L),
    room_price_change("Room price has change,please refresh！", "", 2003L),

    hotel_not_exists("Can't find this hotel","",3001L),

    pay_error("Insufficient account balance or incorrect information","",4001L),
    ;

    private String desc;
    private Long code;
    private String key;

    ResponseCode(String desc, String key, Long code) {
        this.desc = desc;
        this.key = key;
        this.code = code;
    }

    @Override
    public Long getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    public String getKey() {
        return this.key;
    }

    /**
     * 创建
     *
     * @return
     */
    public ResponseResult create() {
        ResponseResult response = new ResponseResult();
        response.setCode(this.getCode());
        response.setMsg(this.getDesc());
        return response;
    }
}
