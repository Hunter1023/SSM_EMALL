package com.hunter.ssm_emall.dao;

import com.hunter.ssm_emall.bean.User;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {

    User getUser(@Param("username") String username, @Param("password") String password);
}
