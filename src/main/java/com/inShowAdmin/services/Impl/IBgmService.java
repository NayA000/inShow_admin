package com.inShowAdmin.services.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.inShowAdmin.mapper.BgmMapper;
import com.inShowAdmin.pojo.Bgm;
import com.inShowAdmin.services.bgmService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class IBgmService implements bgmService {
	@Autowired
	private BgmMapper bmapper;

	@Override
	public boolean delectBgm(String[] sid) {
		for(String id:sid) {
			Example e = new Example(Bgm.class);
			Criteria c = e.createCriteria();
			c.andEqualTo("id", id);
			bmapper.deleteByExample(e);
		}
		return true;
	}

	@Override
	public List<Bgm> getAllBgm(int page) {
		if(bmapper==null) System.out.println("++++++++++-++++++++++++--------");
		PageHelper.startPage(page, 15);
		List<Bgm> bl = bmapper.selectAll();
		return bl;
	}

	@Override
	public List<Bgm> findBgmByKey(String key,int page) {
		List<Bgm> bl = new ArrayList<>();
		try {
			bl = new solrSearch().selectBgmByKey(key, page);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bl;
	}

	@Override
	public boolean addBgm(Bgm bgm) {
		bmapper.insert(bgm);
		return true;
	}

}
