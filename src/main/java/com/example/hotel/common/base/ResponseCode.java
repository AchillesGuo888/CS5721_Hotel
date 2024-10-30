package com.example.hotel.common.base;

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
    email_error_rules("Please enter the correct email address.","", 1011L),
    code_false("Please enter the correct validation code", "", 1007L),
    param_error("Parameter Error", "", 1001L),
    password_error_rules("Password format error", "", 1005L),
    email_has_exist("The email has existed", "", 1010L),
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

    @Override
    public String getEnDesc() {
        return null;
    }


}