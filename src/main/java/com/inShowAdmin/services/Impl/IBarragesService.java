package com.inShowAdmin.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.inShowAdmin.mapper.BarragesMapper;
import com.inShowAdmin.pojo.Barrages;
import com.inShowAdmin.services.barragesService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class IBarragesService implements barragesService {
	@Autowired
	private BarragesMapper bmapper;
	public boolean delectBarrages(String[] bid) {
		for(String id : bid) {
			Example e = new Example(Barrages.class);
			Criteria c = e.createCriteria();
			c.andEqualTo("id", id);
			bmapper.deleteByExample(e);
		}
		return true;
	}

	public List<Barrages> getAllBarrages(int page) {
		List<Barrages> bl = new ArrayList<>();
		PageHelper.startPage(page, 15);
		bl = bmapper.selectAll();
		return bl;
	}

}
