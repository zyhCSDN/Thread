//package com.debug.springboot.test;/**
// * Created by Administrator on 2019/10/8.
// */
//
//import com.debug.springboot.server.MainApplication;
//import com.debug.springboot.server.mongo.MongoUser;
//import com.debug.springboot.server.repository.UserRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.List;
//
///**
// * @Author:debug (SteadyJack)
// * @Date: 2019/10/8 22:57
// **/
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = MainApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class MongoTest {
//
//    private static final Logger log= LoggerFactory.getLogger(MongoTest.class);
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void saveUser() throws Exception {
//        MongoUser user=new MongoUser();
//        user.setId(10010L);
//        user.setAge(21);
//        user.setName("稳杰");
//        userRepository.saveUser(user);
//
//        user=new MongoUser();
//        user.setId(10011L);
//        user.setAge(23);
//        user.setName("大圣");
//        userRepository.saveUser(user);
//    }
//
//    @Test
//    public void findUserById(){
//        final Long id=10010L;
//        MongoUser user=userRepository.findById(id);
//        log.info("----查询结果：{}",user);
//    }
//
//    @Test
//    public void updateUser(){
//        final Long id=10010L;
//        MongoUser user=new MongoUser();
//        user.setAge(22);
//        user.setName("阿修罗");
//        user.setId(id);
//        userRepository.updateUser(user);
//    }
//
//    @Test
//    public void deleteUserById(){
//        final Long id=10010L;
//        userRepository.deleteById(id);
//    }
//
//    @Test
//    public void findAll(){
//        List<MongoUser> list=userRepository.findAll();
//        log.info("----查询结果：{}",list);
//    }
//}