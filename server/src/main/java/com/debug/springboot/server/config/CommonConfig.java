package com.debug.springboot.server.config;/**
 * Created by Administrator on 2019/10/13.
 */

import com.github.dadiyang.equator.Equator;
import com.github.dadiyang.equator.FieldBaseEquator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/10/13 23:15
 **/
@Configuration
public class CommonConfig {

    @Bean
    public Equator equator(){
        Equator equator=new FieldBaseEquator();
        return equator;
    }
}

















