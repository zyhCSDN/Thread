package com.debug.springboot.server.interview.Date20190925.two;/**
 * Created by Administrator on 2019/9/25.
 */

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/9/25 20:21
 **/
@Component
public class SpringContextUtil implements ApplicationContextAware{

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context=context;
    }

    //获取spring应用上下文（容器）-进而准备获取相应的bean
    public ApplicationContext getContext(){
        return context;
    }
}






































