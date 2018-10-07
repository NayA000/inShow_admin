package com.inShowAdmin.controller;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inShowAdmin.pojo.Topics;
import com.inShowAdmin.pojo.Videos;
import com.inShowAdmin.services.topicService;
import com.inShowAdmin.utils.JSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@Api(value="话题相关controller")
@RestController
@RequestMapping("/topic")
public class topicController {
	@Autowired
	private topicService tservice;
	
	@ApiOperation(value="获取话题列表", notes="获取话题列表的接口")
	@ApiImplicitParam(name="page", value="页数（从0开始）", required=true, 
						dataType="int", paramType="query")
	@GetMapping("/getAll")
	public JSONResult getAll(int page) {
		List<Topics> tlist = tservice.getAllTopic(page);
		return JSONResult.ok(tlist);
	}
	
	@ApiOperation(value="搜索话题", notes="搜索话题的接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="key", value="关键字", required=true, 
				dataType="String", paramType="query"),
		@ApiImplicitParam(name="page", value="页数（从0开始）", required=true, 
						dataType="int", paramType="query")
	})
	@GetMapping("/search")
	public JSONResult Vsearch(String key,int page) throws SolrServerException, IOException {
		if(key==null) key="";
		List<Topics> tlist = tservice.findTopicByKey(key, page);
		return JSONResult.ok(tlist);
	}
	@ApiOperation(value="删除话题", notes="删除话题的接口")
	@ApiImplicitParam(name="tid", value="话题id", required=true, 
						dataType="String[]", paramType="query")
	@GetMapping("/delect")
	public JSONResult del(String[] tid) {
		if(tservice.delectTopic(tid))
			return JSONResult.ok();
		return JSONResult.errorMsg("删除topic失败");
		
	}
}
