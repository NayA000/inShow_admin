package com.inShowAdmin.services.Impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.inShowAdmin.mapper.TopicsMapper;
import com.inShowAdmin.pojo.Topics;
import com.inShowAdmin.pojo.Videos;
import com.inShowAdmin.services.topicService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class ITopicService implements topicService {
	@Autowired
	private TopicsMapper tmapper;
	@Override
	public boolean delectTopic(String[] sid) {
		for(String id:sid) {
			Example e = new Example(Topics.class);
			Criteria c = e.createCriteria();
			c.andEqualTo("id", id);
			tmapper.deleteByExample(e);
		}
		return true;
	}

	@Override
	public List<Topics> getAllTopic(int page) {
		PageHelper.startPage(page, 15);
		List<Topics> tl = tmapper.selectAll();
		return tl;
		
	}

	@Override
	public List<Topics> findTopicByKey(String key,int page) throws ParseException {
		List<Topics> tl = new ArrayList<>();
		try {
			tl = new solrSearch().selectTopicByKey(key, page);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tl;
	}

}
