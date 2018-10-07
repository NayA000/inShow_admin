package com.inShowAdmin.services;

import java.util.List;

import com.inShowAdmin.pojo.Users;

public interface userService {
	/**
	 * 获取用户列表
	 * @param page
	 * @return
	 */
	public List<Users> getAllUser(int page);
	/**
	 * 根据关键字查找用户
	 * @param key
	 * @return
	 */
	public List<Users> findUserByKey(String key,int page);

}
