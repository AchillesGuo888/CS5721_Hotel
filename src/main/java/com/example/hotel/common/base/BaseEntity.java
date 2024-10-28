package com.example.hotel.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.example.hotel.common.constant.CommonConstant;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 言曌
 * @date 2019-08-07 00:28
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * ID，自动生成
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 删除状态：1删除，0未删除
     */
    @TableField(value = "del_flag")
    @TableLogic
    private Integer delFlag = CommonConstant.STATUS_NORMAL;

    /**
     * 创建人手机号
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
