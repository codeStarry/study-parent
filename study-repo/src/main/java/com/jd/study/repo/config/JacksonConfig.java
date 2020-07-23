package com.jd.study.repo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 配置jackson序列化方式
 * @author lsy
 * @date 2020-07-18
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return build -> {
            build.locale(Locale.CHINA);
            build.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
            build.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
            build.modules(new DateTimeModule());
        };
    }
}
