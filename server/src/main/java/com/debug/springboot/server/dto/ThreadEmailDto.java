package com.debug.springboot.server.dto;

import com.debug.springboot.server.service.EmailSendService;

import java.util.concurrent.Callable;

/**
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/8 10:18
 **/
public class ThreadEmailDto implements Callable<Boolean>{

    private String userEmail;
    private String subject;
    private String content;

    private EmailSendService emailSendService;

    public ThreadEmailDto(String userEmail, String subject, String content, EmailSendService emailSendService) {
        this.userEmail = userEmail;
        this.subject = subject;
        this.content = content;
        this.emailSendService = emailSendService;
    }

    //TODO:线程的核心任务-即要做的事情
    @Override
    public Boolean call() throws Exception {
        emailSendService.sendSimpleEmail(subject,content,userEmail);
        return true;
    }
}