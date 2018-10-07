package com.inShowAdmin;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inShowAdmin.mapper.BgmMapper;
import com.inShowAdmin.pojo.Bgm;
import com.inShowAdmin.pojo.Users;
import com.inShowAdmin.services.bgmService;
import com.inShowAdmin.services.Impl.IBgmService;
import com.inShowAdmin.services.Impl.solrSearch;
@RunWith(SpringRunner.class)
@SpringBootTest
public class mymain {
	@Autowired
	public bgmService bservice;
	
	public void aaa() throws SolrServerException, IOException {
		List<Users> l1 = new solrSearch().selectUserByKey("", 0);
		for(Users u : l1) {
			System.out.println("--------"+u.toString());
		}
	}
	@Test
	public void bbb() {
		List<Bgm> bl = bservice.getAllBgm(0);
		for(Bgm bgm : bl) {
			System.out.println("-----"+bgm.toString());
		}
	}
}
