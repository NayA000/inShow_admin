package com.inShowAdmin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.inShowAdmin.pojo.Users;
import com.inShowAdmin.utils.MyMapper;
@Mapper
public interface UsersMapper extends MyMapper<Users> {
}