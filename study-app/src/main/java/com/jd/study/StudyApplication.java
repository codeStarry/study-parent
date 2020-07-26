package com.jd.study;

import com.jd.study.repo.config.Constants;
import com.jd.study.repo.config.R;
import com.jd.study.repo.config.StudyException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * 启动类
 * @author lsy
 * @date 2020-07-18
 */
@RestControllerAdvice
@SpringBootApplication
public class StudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
        /*MyCodeGeneratorProcessor processor = SpringUtils.getBean(MyCodeGeneratorProcessor.class);
        processor.codeGenerator();*/
    }

    /**
     * 异常拦截处理
     * @param ex
     * @return
     */
    @ExceptionHandler(StudyException.class)
    public R<String> exception(StudyException ex) {
        return R.failure(ex.getMessage());
    }

    /**
     * 创建文件上传临时路径
     * @return
     */
    @Bean
    public MultipartConfigElement createTemporaryDirectory() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //获取临时路径
        String path = System.getProperty("user.dir") + Constants.DOWNLOAD_PATH;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        factory.setLocation(path);
        return factory.createMultipartConfig();
    }
}
