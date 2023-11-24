package com.alibou.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TokenMapper {
    
    @Select("SELECT _user.role " +
    "FROM token " +
    "INNER JOIN _user ON token.user_id = _user.id " +
    "WHERE token.token = #{tokenValue}")
    String getRoleByTokenValue(@Param("tokenValue") String tokenValue);

}
