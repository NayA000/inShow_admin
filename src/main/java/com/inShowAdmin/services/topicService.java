package com.inShowAdmin.services;

import java.text.ParseException;
import java.util.List;

import com.inShowAdmin.pojo.Topics;

public interface topicService {
	/**
	 * 按id删除话题
	 * @param vid   id数组
	 * @return
	 */
	public boolean delectTopic(String[] sid);
	/**
	 * 获取话题列表
	 * @param page
	 * @return
	 */
	public List<Topics> getAllTopic(int page);
	/**
	 * 根据关键字查找话题
	 * @param key
	 * @return
	 * @throws ParseException 
	 */
	public List<Topics> findTopicByKey(String key,int page) throws ParseException;

}
