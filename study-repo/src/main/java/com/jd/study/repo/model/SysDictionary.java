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
 * 系统字典表
 *
 * @author lsy
 * @since 2020-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dictionary")
@ApiModel(value = "SysDictionary对象", description = "系统字典表")
public class SysDictionary extends Model<SysDictionary> {


    /**
     * 赋予初值的构造方法
     */
    public SysDictionary defv() {
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

    @ApiModelProperty(value = "逻辑删   0删除   1正常")
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

    @ApiModelProperty(value = "键")
    @TableField("key")
    private String key;

    @ApiModelProperty(value = "值")
    @TableField("value")
    private String value;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
