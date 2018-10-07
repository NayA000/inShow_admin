package com.inShowAdmin.services.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.inShowAdmin.mapper.UsersMapper;
import com.inShowAdmin.pojo.Users;
import com.inShowAdmin.services.userService;
@Service
public class IUserServices implements userService {
	@Autowired
	private UsersMapper umapper;
	
	@Override
	public List<Users> getAllUser(int page) {
		PageHelper.startPage(page, 15);
		List<Users> list= umapper.selectAll();
		return list;
		
	}

	@Override
	public List<Users> findUserByKey(String key ,int page) {
		List<Users> ul = new ArrayList<>();
		try {
			ul = new solrSearch().selectUserByKey(key, page);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return ul;
	}

}
