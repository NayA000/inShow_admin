package com.inShowAdmin.services;

import java.util.List;

import com.inShowAdmin.pojo.Barrages;

public interface barragesService {
	/**
	 * 按id删除举报洗信息
	 * @param vid   id数组
	 * @return
	 */
	public boolean delectBarrages(String[] bid);
	/** 
	 * 获取举报信息列表
	 * @param page
	 * @return
	 */
	public List<Barrages> getAllBarrages(int page);
	
}
