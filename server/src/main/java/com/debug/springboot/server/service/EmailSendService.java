package com.debug.springboot.server.service;

import com.debug.springboot.model.mapper.primary.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/8 10:28
 **/
@Service
public class EmailSendService {

    private static final Logger log= LoggerFactory.getLogger(EmailSendService.class);

    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender mailSender;

    //TODO:发送简单的邮件消息
    public void sendSimpleEmail(final String subject,final String content,final String ... tos){
        try {
            SimpleMailMessage message=new SimpleMailMessage();
            message.setSubject(subject);
            message.setText(content);
            message.setTo(tos);
            message.setFrom(env.getProperty("mail.send.from"));
            mailSender.send(message);

            log.info("----发送简单的邮件消息完毕--->");
        }catch (Exception e){
            log.error("--发送简单的邮件消息,发生异常：",e.fillInStackTrace());
        }
    }
}