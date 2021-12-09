package com.debug.springboot.server.service;

import com.debug.springboot.model.entity.primary.User;
import com.debug.springboot.server.dto.UserDto;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Spring+JdbcTemplate实战
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/11/11 21:34
 **/
@Service
public class JdbcTemplateService {

    private static final Logger log= LoggerFactory.getLogger(JdbcTemplateService.class);

    private static final String insertSql="insert into user (name, code, email) values (?,?,?)";

    private static final String queryOneSql="SELECT id,`name`,`code`,email FROM `user` WHERE id=?";

    private static final String queryListSql="SELECT id,`name`,`code`,email FROM `user` WHERE 1=1 AND (name LIKE ? OR `code` LIKE ? OR email LIKE ?)";

    private static final String deleteSql="DELETE FROM `user` WHERE id=?";


    @Resource(name = "primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    //TODO:新增
    public int addUser(UserDto dto) throws Exception{
        return primaryJdbcTemplate.update(insertSql, ps -> {
            ps.setString(1,dto.getName());
            ps.setString(2,dto.getCode());
            ps.setString(3,dto.getEmail());
        });
    }

    //TODO:查询-一个 - 结果集与实体对象映射
    public User queryUserById(final Integer id){
        try {
            return primaryJdbcTemplate.queryForObject(queryOneSql, new Object[]{id}, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int i) throws SQLException {
                    User entity=new User();
                    entity.setId(rs.getInt("id"));
                    entity.setName(rs.getString("name"));
                    entity.setCode(rs.getString("code"));
                    entity.setEmail(rs.getString("email"));
                    return entity;
                }
            });
        }catch (Exception e){
            log.error("spring-jdbcTemplate-查询用户信息发生异常：id={}",id,e.fillInStackTrace());
        }
        return null;
    }

    //TODO:列表查询一
    public List<User> queryListV1(final String search) throws Exception{
        return primaryJdbcTemplate.query(queryListSql, new Object[]{"%" + search + "%","%" + search + "%","%" + search + "%"}, new RowMapper<User>() {
            User entity;
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                entity=new User();
                entity.setId(rs.getInt("id"));
                entity.setName(rs.getString("name"));
                entity.setCode(rs.getString("code"));
                entity.setEmail(rs.getString("email"));
                return entity;
            }
        });
    }

    //TODO:列表查询二
    public List<User> queryListV2(final String search) throws Exception{
        return primaryJdbcTemplate.query(queryListSql,
                new Object[]{"%" + search + "%","%" + search + "%","%" + search + "%"}, new BeanPropertyRowMapper<>(User.class));
    }

    //TODO:删除
    public int delete(final Integer id){
        return primaryJdbcTemplate.update(deleteSql,id);
    }
}































