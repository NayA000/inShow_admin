package com.inShowAdmin.services.Impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.inShowAdmin.mapper.VideosMapper;
import com.inShowAdmin.pojo.Bgm;
import com.inShowAdmin.pojo.Videos;
import com.inShowAdmin.services.vedioService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class IVideoService implements vedioService {
	@Autowired
	private VideosMapper vmapper;
	@Override
	public boolean delectVideo(String[] sid) {
		for(String id:sid) {
			Example e = new Example(Videos.class);
			Criteria c = e.createCriteria();
			c.andEqualTo("id", id);
			vmapper.deleteByExample(e);
		}
		return true;
	}

	@Override
	public List<Videos> getAllVideo(int page) {
		PageHelper.startPage(page, 15);
		List<Videos> vl = vmapper.selectAll();
		return vl;
	}

	@Override
	public List<Videos> findVideoByKey(String key , int page) throws ParseException {
		List<Videos> vl = new ArrayList<>();
		try {
			vl = new solrSearch().selectVideoByKey(key, page);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vl;
	}

	@Override
	public boolean sealVideo(String vid) {
		Example e = new Example(Videos.class);
		Criteria c = e.createCriteria();
		c.andEqualTo("id", vid);
		List<Videos> a = vmapper.selectByExample(e);
		if(a!=null) {
			Videos v = a.get(0);
			v.setStatus(2);
			vmapper.updateByPrimaryKey(v);
			return true;
		}
		return false;
	}

}
