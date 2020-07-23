package com.jd.study.repo.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.jd.study.repo.config.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 *
 * @author lsy
 * @since 2020-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("s_user")
@ApiModel(value = "SUser对象", description = "用户表")
public class SUser extends Model<SUser> {


    /**
     * 赋予初值的构造方法
     */
    public SUser defv() {
        setType(Constants.NORMAL);
        setStatus(Constants.NORMAL);
        setCreateTime(LocalDateTime.now());
        setUpdateTime(getCreateTime());
        return this;
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "逻辑删    0删除   1正常")
    @TableField("status")
    @TableLogic
    private Integer status;

    @ApiModelProperty(value = "业务类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "用户真实姓名")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "生日")
    @TableField("birthday")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "用户头像")
    @TableField("avator_img")
    private String avatorImg;

    @ApiModelProperty(value = "个性签名")
    @TableField("user_signature")
    private String userSignature;

    @ApiModelProperty(value = "注册方式")
    @TableField("reg_way")
    private String regWay;

    @ApiModelProperty(value = "设备编码")
    @TableField("device_code")
    private String deviceCode;

    @ApiModelProperty(value = "是否实名认证   0：未认证   1：已认证")
    @TableField("real_name_auth")
    private Integer realNameAuth;

    @ApiModelProperty(value = "银行卡信息    JSON格式   {code:银行卡号,  name:银行名称，phone:手机号}")
    @TableField("bank_info")
    private String bankInfo;

    @ApiModelProperty(value = "注册IP")
    @TableField("reg_ip")
    private String regIp;

    @ApiModelProperty(value = "用户最近一次登录IP")
    @TableField("login_ip")
    private String loginIp;

    @ApiModelProperty(value = "登录时间")
    @TableField("login_time")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "下线时间")
    @TableField("out_time")
    private LocalDateTime outTime;

    @ApiModelProperty(value = "身份证信息   JSON格式   {card:身份证号, address:户籍地址, bornDate:出生年月日, expir:过期时间 }")
    @TableField("id_card_info")
    private String idCardInfo;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
