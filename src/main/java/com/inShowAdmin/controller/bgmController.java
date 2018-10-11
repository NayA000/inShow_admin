package com.inShowAdmin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.inShowAdmin.pojo.Bgm;
import com.inShowAdmin.pojo.Topics;
import com.inShowAdmin.services.bgmService;
import com.inShowAdmin.utils.JSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="bgm相关controller")
@RestController
@RequestMapping("/bgm")
public class bgmController {
	@Autowired
	private bgmService bservice;
	@Autowired
	private Sid sid;
	@ApiOperation(value="获取bgm列表", notes="获取bgm列表的接口")
	@ApiImplicitParam(name="page", value="页数（从0开始）", required=true, 
						dataType="int", paramType="query")
	@GetMapping("/getAll")
	public JSONResult getAll(int page) {
		List<Bgm> list = bservice.getAllBgm(page);
		return JSONResult.ok(list);
	}
	
	@ApiOperation(value="搜索bgm", notes="搜索bgm的接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="key", value="关键字", required=true, 
				dataType="String", paramType="query"),
		@ApiImplicitParam(name="page", value="页数（从0开始）", required=true, 
						dataType="int", paramType="query")
	})
	@GetMapping("/search")
	public JSONResult Vsearch(String key,int page) throws SolrServerException, IOException {
		System.out.println("----------"+key+"----------");
		if(key==null) key="";
		List<Bgm> list = bservice.findBgmByKey(key, page);
		return JSONResult.ok(list);
	}
	@ApiOperation(value="删除bgm", notes="删除bgm的接口")
	@ApiImplicitParam(name="bid", value="bgm_id", required=true, 
						dataType="String", paramType="query")
	@GetMapping("/delect")
	public JSONResult del(String[] bid) {
		if(bservice.delectBgm(bid))
			return JSONResult.ok();
		return JSONResult.errorMsg("删除topic失败");
	}
	
	
	
	
	
	//-------------------------------------------
	//未测试！！！！！！
	//-------------------------------------------
	@ApiOperation(value="上传bgm", notes="上传bgm的接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="bgmName", value="bgm名称", required=true, 
				dataType="String", paramType="query"),
		@ApiImplicitParam(name="author", value="作者名称", required=true, 
				dataType="String", paramType="query")
	})
	@PostMapping(value="/upload", headers="content-type=multipart/form-data")
	public JSONResult uploadCover(String bgmName,
				String author,
				@ApiParam(value="bgm文件", required=true)
				MultipartFile file) throws Exception {
		
		if (StringUtils.isBlank(bgmName) || StringUtils.isBlank(author)) {
			return JSONResult.errorMsg("bgm名称与作者名不能为空");
		}
		
		// 文件保存的命名空间
//		String fileSpace = "C:/imooc_videos_dev";
		// 保存到数据库中的相对路径
		//"E:/inShow_Video/bgm"
		/*String uploadPathDB = "E:/inShow_Video/bgm";*/
		String uploadPathDB = "";
		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;
		// 文件上传的最终保存路径
		String finalBgmPath = "";
		try {
			if (file != null) {
				
				String fileName = file.getOriginalFilename();
				if (StringUtils.isNotBlank(fileName)) {
					
					finalBgmPath = "E:/inShow_Video/bgm/" + fileName;
					// 设置数据库保存的路径
					uploadPathDB += ("/bgm/" + fileName);
					
					File outFile = new File(finalBgmPath);
					if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
						// 创建父文件夹
						outFile.getParentFile().mkdirs();
					}
					
					fileOutputStream = new FileOutputStream(outFile);
					inputStream = file.getInputStream();
					IOUtils.copy(inputStream, fileOutputStream);
				}
				
			} else {
				return JSONResult.errorMsg("上传出错...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JSONResult.errorMsg("上传出错...");
		} finally {
			if (fileOutputStream != null) {
				fileOutputStream.flush();
				fileOutputStream.close();
			}
		}
		
		//此处补充写入数据库的代码....
		Bgm bgm = new Bgm();
		bgm.setId(sid.nextShort());
		bgm.setName(bgmName);
		bgm.setAuthor(author);
		bgm.setPath(uploadPathDB);
		bgm.setChooseCount(0);
		bservice.addBgm(bgm);
		
		return JSONResult.ok();
	}

}









