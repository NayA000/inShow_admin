package com.inShowAdmin.controller;


import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inShowAdmin.pojo.Videos;
import com.inShowAdmin.services.vedioService;
import com.inShowAdmin.services.Impl.solrSearch;
import com.inShowAdmin.utils.JSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="视频相关controller")
@RestController
@RequestMapping("/video")
public class videoController {
	@Autowired
	private vedioService vservice;
	
	
	@ApiOperation(value="获取视频列表", notes="获取视频列表的接口")
	@ApiImplicitParam(name="page", value="页数（从0开始）", required=true, 
						dataType="int", paramType="query")
	@GetMapping("/getAll")
	public JSONResult getAll(int page) {
		List<Videos> vlist = vservice.getAllVideo(page);
		return JSONResult.ok(vlist);
	}
	
	@ApiOperation(value="搜索视频", notes="搜索视频的接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="key", value="关键字", required=true, 
				dataType="String", paramType="query"),
		@ApiImplicitParam(name="page", value="页数（从0开始）", required=true, 
						dataType="int", paramType="query")
	})
	@GetMapping("/search")
	public JSONResult Vsearch(String key,int page) throws SolrServerException, IOException, ParseException {
		if(key==null) key="";
		List<Videos> vlist = vservice.findVideoByKey(key, page);
		return JSONResult.ok(vlist);
	}
	@ApiOperation(value="封视频", notes="封视频的接口")
	@ApiImplicitParam(name="vid", value="视频id", required=true, 
						dataType="String", paramType="query")
	@GetMapping("/seal")
	public JSONResult vseal(String[] vid) {
		if(vservice.sealVideo(vid)) 
			return JSONResult.ok();
		return JSONResult.errorMsg("失败");
	}

}
