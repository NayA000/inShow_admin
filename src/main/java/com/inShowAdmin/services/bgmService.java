package com.inShowAdmin.services;

import java.util.List;

import com.inShowAdmin.pojo.Bgm;

public interface bgmService {
	/**
	 * 按id删除bgm
	 * @param vid   id数组
	 * @return
	 */
	public boolean delectBgm(String[] sid);
	/**
	 * 获取bgm列表
	 * @param page
	 * @return
	 */
	public List<Bgm> getAllBgm(int page);
	/**
	 * 根据关键字查找bgm
	 * @param key
	 * @return
	 */
	public List<Bgm> findBgmByKey(String key,int page);
	/**
	 * 添加bgm
	 * @param bgm
	 * @return
	 */
	public boolean addBgm(Bgm bgm);
	

}






