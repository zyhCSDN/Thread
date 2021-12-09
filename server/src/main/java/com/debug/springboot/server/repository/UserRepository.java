//package com.debug.springboot.server.repository;/**
// * Created by Administrator on 2019/10/8.
// */
//
//import com.debug.springboot.server.mongo.MongoUser;
//import com.mongodb.client.result.UpdateResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * @Author:debug (SteadyJack)
// * @Date: 2019/10/8 22:48
// **/
//@Component
//public class UserRepository {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//
//    public void saveUser(MongoUser user){
//        mongoTemplate.save(user);
//    }
//
//    public MongoUser findById(Long id){
//        Query query=new Query(Criteria.where("id").is(id));
//        return mongoTemplate.findOne(query,MongoUser.class);
//    }
//
//    public long updateUser(MongoUser user){
//        Query query=new Query(Criteria.where("id").is(user.getId()));
//        Update update=new Update().set("name",user.getName()).set("age",user.getAge());
//        UpdateResult result=mongoTemplate.updateFirst(query,update,MongoUser.class);
//
//        if (result!=null){
//            return result.getMatchedCount();
//        }else{
//            return 0;
//        }
//    }
//
//    public void deleteById(Long id){
//        Query query=new Query(Criteria.where("id").is(id));
//        mongoTemplate.remove(query,MongoUser.class);
//    }
//
//    public List<MongoUser> findAll(){
//        return mongoTemplate.findAll(MongoUser.class);
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
