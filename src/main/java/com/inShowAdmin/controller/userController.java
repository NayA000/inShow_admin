package com.inShowAdmin.controller;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inShowAdmin.pojo.Users;
import com.inShowAdmin.services.userService;
import com.inShowAdmin.utils.JSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@Api(value="用户相关controller")
@RestController
@RequestMapping("/user")
public class userController {
	@Autowired
	private userService uservice;
	
	@ApiOperation(value="获取用户列表", notes="获取用户列表的接口")
	@ApiImplicitParam(name="page", value="页数（从0开始）", required=true, 
						dataType="int", paramType="query")
	@GetMapping("/getAll")
	public JSONResult getUser(int page) {
		
		try {
			List<Users> ulist = uservice.getAllUser(page);
			return JSONResult.ok(ulist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONResult.errorMsg("获取失败");
	}
	
	
	@ApiOperation(value="搜索用户", notes="搜索用户的接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="key", value="关键字", required=true, 
				dataType="String", paramType="query"),
		@ApiImplicitParam(name="page", value="页数（从0开始）", required=true, 
						dataType="int", paramType="query")
	})
	@GetMapping("/search")
	public JSONResult userSearch(String key,int page) throws SolrServerException, IOException {
		try {
			List<Users> ulist = uservice.findUserByKey(key, page);
			return JSONResult.ok(ulist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONResult.errorMsg("获取失败");
	}
	/*@ApiOperation(value="上传视频", notes="上传视频的接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId", value="用户id", required=true, 
				dataType="String", paramType="form"),
		@ApiImplicitParam(name="bgmId", value="背景音乐id", required=false, 
				dataType="String", paramType="form"),
		@ApiImplicitParam(name="videoSeconds", value="背景音乐播放长度", required=true, 
				dataType="String", paramType="form"),
		@ApiImplicitParam(name="videoWidth", value="视频宽度", required=true, 
				dataType="String", paramType="form"),
		@ApiImplicitParam(name="videoHeight", value="视频高度", required=true, 
				dataType="String", paramType="form"),
		@ApiImplicitParam(name="desc", value="视频描述", required=false, 
				dataType="String", paramType="form")
	})*/

}
