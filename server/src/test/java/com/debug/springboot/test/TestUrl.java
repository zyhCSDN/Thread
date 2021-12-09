package com.debug.springboot.test;/**
 * Created by Administrator on 2019/9/12.
 */

import com.debug.springboot.model.entity.primary.User;
import com.debug.springboot.model.entity.second.SysConfig;
import com.debug.springboot.model.mapper.primary.UserMapper;
import com.debug.springboot.server.MainApplication;
import com.debug.springboot.server.retryer.RetryService;
import com.debug.springboot.server.utils.CommonUtil;
import com.debug.springboot.server.utils.SnowFlake;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/9/12 23:05
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestUrl {

    private static final Logger log= LoggerFactory.getLogger(TestUrl.class);

    private static final String URL_Value="http://localhost:8081/technology/base/info";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserMapper userMapper;

    //在项目中测试发起对某个请求链接的访问
    @Test
    public void method1() throws Exception{
        log.info("---开始测试---");

        URL url=new URL(URL_Value);
        ResponseEntity<String> responseEntity=testRestTemplate.getForEntity(url.toString(),String.class);
        log.info("---请求得到响应信息---\n{}\n状态信息：{}\n{}",responseEntity.getBody(),responseEntity.getStatusCodeValue(),responseEntity.getStatusCode());

    }

    public static final SnowFlake SNOW_FLAKE=new SnowFlake(3,2);

    @Test
    public void method2() throws Exception{
        log.info("---开始测试---");

        String namePrefix="修罗-";
        Long total=100L;

        User user;
        String code;
        while (total-->0){
            code=String.valueOf(SNOW_FLAKE.nextId());
            user=new User();
            user.setCode(code);
            user.setName(namePrefix+code);
            userMapper.insertSelective(user);
        }

        /*ExecutorService executorService=Executors.newCachedThreadPool();
        executorService.execute(() -> {
            User user;
            String code;
            for (int i=0;i<total;i++){
                code=String.valueOf(SNOW_FLAKE.nextId());
                user=new User();
                user.setCode(code);
                user.setName(namePrefix+code);
                userMapper.insertSelective(user);
            }
        });*/
    }

    @Test
    public void method3() throws Exception{
        //待批量查询的 列元素 列表
        List<Long> list=userMapper.selectAllIds();

        List<User> users=userMapper.selectByIds(list);
        for (User user: users  ) {
            log.info("--获取查询结果列表：{}",user);
        }
    }

    @Test
    public void method4() throws Exception{
        //待批量查询的 列元素 列表
        List<Long> list=userMapper.selectAllIds();

        //利用mybatis $ 静态取值的特性，将其转化为 元素拼接的String 常量值
        List<User> users=userMapper.selectByStrIds(Joiner.on(",").join(list));
        log.info("--获取查询结果列表：{}",users);
    }


    @Test
    public void method5() throws Exception{
        for (int i=0;i<=1000;i++){
            userMapper.insertCode(i);
        }
    }


    @Test
    public void method6() throws Exception{
        final String fileOne="E:\\srv\\Files\\FileOne.txt";

        StringBuilder sb=new StringBuilder();

        char[] buf=new char[1024];
        FileReader reader=new FileReader(fileOne);
        while (reader.read(buf)>0){
            sb.append(buf);
        }
        log.info("----读取到的文件的内容：{}",sb.toString());


        final String fileTwo="E:\\srv\\Files\\FileTwo.txt";
        //BufferedWriter writer=new BufferedWriter(new FileWriter(fileTwo));
        FileWriter writer=new FileWriter(fileTwo);
        writer.write(sb.toString());
        writer.flush();
    }



    @Test
    public void method7() throws Exception{
        Map<String,Object> map= Maps.newHashMap();

        List<String> cubList= Lists.newArrayList("104708","73389");
        String cubNo=CommonUtil.concatStrToChar(Joiner.on(",").join(cubList),",");
        map.put("cubNo",cubNo);

        List<User> resList=userMapper.selectByCodes(map);
        log.info("---结果：{}",resList);
    }


    @Autowired
    private RetryService retryService;

    @Test
    public void method8() throws Exception{
        final Integer id=12;

        SysConfig entity=retryService.getConfigInfo(id);
        log.info("---结果：{}",entity);
    }

}































