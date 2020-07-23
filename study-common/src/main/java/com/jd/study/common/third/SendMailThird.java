package com.jd.study.common.third;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 邮件工具类
 * @author lsy
 * @date 2020-7-16
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SendMailThird {

    private final JavaMailSender mailSender;

    private static JavaMailSender sender;

    private static String userName;

    private static String subject;

    @Value("${spring.mail.username}")
    public void setUserName(String userName) {
        SendMailThird.userName = userName;
    }

    @Value("${spring.mail.subject}")
    public void setSubject(String subject) {
        SendMailThird.subject = subject;
    }

    @PostConstruct
    private void init() {
        SendMailThird.sender = this.mailSender;
    }

    /**
     * 发送普通邮件
     * @param content   邮件内容
     * @param email     目标邮箱
     */
    public static void sender(String content, String...email) throws IllegalAccessException{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(content);
        message.setTo(email);
        message.setFrom(userName);
        sender.send(message);
    }
}
