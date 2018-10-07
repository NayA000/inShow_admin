package com.inShowAdmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inShowAdmin.pojo.Barrages;
import com.inShowAdmin.pojo.Topics;
import com.inShowAdmin.services.barragesService;
import com.inShowAdmin.utils.JSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value="举报相关controller")
@RestController
@RequestMapping("/barrage")
public class barrageController {
	@Autowired
	private barragesService bservice;
	
	@ApiOperation(value="获取话题列表", notes="获取话题列表的接口")
	@ApiImplicitParam(name="page", value="页数（从0开始）", required=true, 
						dataType="int", paramType="query")
	@GetMapping("/getAll")
	public JSONResult getAll(int page) {
		List<Barrages> list = bservice.getAllBarrages(page);
		return JSONResult.ok(list);
	}
	
	@ApiOperation(value="删除举报", notes="删除举报的接口")
	@ApiImplicitParam(name="tid", value="话题id", required=true, 
						dataType="String[]", paramType="query")
	@GetMapping("/delect")
	public JSONResult del(String[] bid) {
		if(bservice.delectBarrages(bid))
			return JSONResult.ok();
		return JSONResult.errorMsg("删除topic失败");
	}

}
