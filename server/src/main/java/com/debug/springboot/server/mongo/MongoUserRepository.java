package com.debug.springboot.server.mongo;

import com.debug.springboot.model.entity.primary.User;
import com.debug.springboot.model.mapper.primary.UserMapper;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/13 11:14
 **/
@Component
public class MongoUserRepository{

    private static final Logger log= LoggerFactory.getLogger(MongoUserRepository.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserMapper userMapper;

    //TODO:新增
    @Transactional(rollbackFor = Exception.class)
    public void save(final MongoUser user) throws Exception{
        User entity=new User();
        BeanUtils.copyProperties(user,entity, "id");
        userMapper.insertSelective(entity);

        user.setId(entity.getId());
        mongoTemplate.save(user);
    }

    //TODO:批量新增
    public void saveAll(final List<MongoUser> list) throws Exception{
        if (list!=null && !list.isEmpty()){
            list.forEach(user -> {
                log.info("-------mongoDB批量新增---------");

                User entity=new User();
                BeanUtils.copyProperties(user,entity, "id");
                userMapper.insertSelective(entity);

                user.setId(entity.getId());
            });

            mongoTemplate.insertAll(list);
        }
    }

    //TODO:查询-列表
    public List<MongoUser> queryAll() throws Exception{
        return mongoTemplate.findAll(MongoUser.class);
    }

    //TODO:查询-主键查询
    public MongoUser queryById(final Integer id) throws Exception{
        return mongoTemplate.findById(id,MongoUser.class);
    }

    //TODO:查询-带条件查询
    public List<MongoUser> queryByName(final String name) throws Exception{
        Criteria criteria=Criteria.where("name").is(name);
        return mongoTemplate.find(Query.query(criteria),MongoUser.class);
    }

    //TODO:更新
    @Transactional(rollbackFor = Exception.class)
    public void update(final MongoUser user) throws Exception{
        User entity=new User();
        BeanUtils.copyProperties(user,entity);
        userMapper.updateByPrimaryKeySelective(entity);

        Query query=Query.query(Criteria.where("id").is(user.getId()));
        Update update=Update.update("name",user.getName()).set("code",user.getCode()).set("email",user.getEmail());
        mongoTemplate.updateFirst(query,update,MongoUser.class);
    }

    //TODO:删除
    @Transactional(rollbackFor = Exception.class)
    public void delete(final Integer id) throws Exception{
        int res=userMapper.deleteByPrimaryKey(id);
        if (res>0){
            Query query=Query.query(Criteria.where("id").is(id));
            mongoTemplate.remove(query,MongoUser.class);
        }
    }

    //TODO:分页查询
    public Map<String,Object> queryPage(Integer pageNo, final Integer pageSize) throws Exception{
        Map<String,Object> resMap= Maps.newHashMap();

        if (pageNo<=0){
            pageNo=1;
        }
        Integer pageStart=(pageNo-1)*pageSize;

        //TODO:正常不带条件的分页
        //Query query=Query.query(Criteria.where("isActive").is(1)).skip(pageStart).limit(pageSize);
        //return mongoTemplate.find(query,MongoUser.class);


        //TODO:正常带条件的分页
        Query query=Query.query(Criteria.where("isActive").is(1)).skip(pageStart).limit(pageSize)
                .with(Sort.by(Sort.Direction.DESC,"code"));

        Long total=mongoTemplate.count(query,MongoUser.class);
        List<MongoUser> list=mongoTemplate.find(query,MongoUser.class);

        resMap.put("total",total);
        resMap.put("list",list);

        return resMap;
    }



}






















































