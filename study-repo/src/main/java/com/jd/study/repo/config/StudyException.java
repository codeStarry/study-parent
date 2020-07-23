package com.jd.study.repo.config;

import lombok.Getter;
import lombok.Setter;

/**
 * 异常视图
 * @author lsy
 * @date 2020-07-18
 */
@Getter
@Setter
public class StudyException extends RuntimeException{

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String message;


    public StudyException(String message) {
        super(message);
        this.message = message;
    }

    public StudyException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
