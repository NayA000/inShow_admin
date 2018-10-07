package com.inShowAdmin.services;

import java.util.List;
import com.inShowAdmin.pojo.Videos;

public interface vedioService {
	/**
	 * 按id删除video
	 * @param vid   id数组
	 * @return
	 */
	public boolean delectVideo(String[] sid);
	/**
	 * 获取video列表
	 * @param page
	 * @return
	 */
	public List<Videos> getAllVideo(int page);
	/**
	 * 根据关键字查找视频
	 * @param key
	 * @return
	 */
	public List<Videos> findVideoByKey(String key, int page);
	/**
	 * 封视频
	 * @param vid
	 * @return
	 */
	public boolean sealVideo(String vid);

}
