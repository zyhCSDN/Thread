//package com.debug.springboot.server.security;
//
//import com.google.common.collect.Lists;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @Author:debug (SteadyJack)
// * @Link: weixin-> debug0868 qq-> 1948831260
// * @Date: 2019/11/11 11:20
// **/
//@Service
//public class UserService implements UserDetailsService{
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        List<GrantedAuthority> list= Lists.newLinkedList();
//        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        return new User(s,"debug",list);
//    }
//}