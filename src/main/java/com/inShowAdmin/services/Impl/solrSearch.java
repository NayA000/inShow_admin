package com.inShowAdmin.services.Impl;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import com.inShowAdmin.pojo.Bgm;
import com.inShowAdmin.pojo.Topics;
import com.inShowAdmin.pojo.Users;
import com.inShowAdmin.pojo.Videos;

@Service
public class solrSearch {
	String solrUrl = "http://192.168.1.6:8888/solr/inShow-admin";
	HttpSolrClient solrClient = null;

	public solrSearch() {
		solrClient = new HttpSolrClient.Builder(solrUrl).build();
//		solrClient = new HttpSolrClient(solrUrl);
	}

	public void Textselectall() throws SolrServerException, IOException {
		SolrQuery query = new SolrQuery();
		query.set("q", "*:*");
		query.setRows(10);
		QueryResponse response = solrClient.query(query);
		SolrDocumentList docs = response.getResults();
		for (SolrDocument doc : docs) {
			System.out.println(doc.get("id"));
		}
	}
	@SuppressWarnings("deprecation")
	public boolean reFleshSolrData() {
		return false;
	}
	/**
	 * 简单查询，提供分页功能
	 * 
	 * @param key
	 * @param start
	 * @param rows
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public SolrDocumentList getDocs(String key, int start, int rows) throws SolrServerException, IOException {
		SolrQuery query = new SolrQuery();
		query.set("q", key);

		if (rows != 0) {
			query.setStart(start);
			query.setRows(rows);
		}
		QueryResponse response = solrClient.query(query);
		SolrDocumentList docs = response.getResults();
		return docs;
	}

	/**
	 * 根据关键字搜索bgm，实现分页，默认每页10条记录
	 * 
	 * @param key
	 * @param page
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public List<Bgm> selectBgmByKey(String key, int page) throws SolrServerException, IOException {
		if(key==null)
			key = "";
		List<Bgm> blist = new ArrayList<Bgm>();
		SolrDocumentList docs = getDocs("b_name:*" + key + "*", page, 10);
		for (SolrDocument doc : docs) {
			Bgm bgm = new Bgm();

			bgm.setAuthor(String.valueOf(doc.get("b_author")));
			// bgm.setChooseCount(Integer.valueOf(String.valueOf(doc.get("b_chooseCount"))));
			bgm.setChooseCount(Integer.valueOf(doc.get("b_chooseCount").toString()));
			System.out.println("------------" + bgm.getChooseCount());
			bgm.setId(String.valueOf(doc.get("id")));
			bgm.setName(String.valueOf(doc.get("b_name")));
			bgm.setPath(String.valueOf(doc.get("b_path")));
			blist.add(bgm);
		}
		return blist;
	}
	/**
	 * 查询user
	 * @param key
	 * @param page
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public List<Users> selectUserByKey(String key,int page) throws SolrServerException, IOException{
		if(key==null)
			key = "";
		List<Users> ulist = new ArrayList<Users>();
		SolrDocumentList docs = getDocs("u_username:*" + key + "*", page, 10);
		for (SolrDocument doc : docs) {
			Users user = new Users();
			try {
				user.setId(doc.getFieldValue("id").toString());
				
				System.out.println("------"+user.getId());
				
				user.setUsername(String.valueOf(doc.getFieldValue("u_username")));
				user.setGender(Integer.valueOf(doc.getFieldValue("u_gender").toString()));
				user.setAvatarurl((doc.getFieldValue("u_avatarUrl")==null?"":doc.getFieldValue("u_avatarUrl")).toString());
				user.setCountry(doc.getFieldValue("u_country").toString());
				user.setProvince(doc.getFieldValue("u_province").toString());
				user.setCity(doc.getFieldValue("u_city").toString());
				user.setNickname(doc.getFieldValue("u_nickname").toString());
				user.setFansCounts(Integer.valueOf(doc.getFieldValue("u_fans_counts").toString()));
				user.setReceiveLikeCounts(Integer.valueOf(doc.getFieldValue("u_receive_like_counts").toString()));
				user.setReportCounts(Integer.valueOf(doc.getFieldValue("u_report_counts").toString()));
				user.setFollowCounts(Integer.valueOf(doc.getFieldValue("u_follow_counts").toString()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ulist.add(user);
		}
		return ulist;
	}
	/**
	 * 查找topic
	 * @param key
	 * @param page
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public List<Topics> selectTopicByKey(String key,int page) throws SolrServerException, IOException, ParseException{
		if(key==null)
			key = "";
		List<Topics> tlist = new ArrayList<Topics>();
		SolrDocumentList docs = getDocs("t_topic_name:*" + key + "*", page, 10);
		for (SolrDocument doc : docs) {
			System.out.println("---------------"+doc.getFieldValue("t_create_time").toString());
			Topics topic = new Topics();
			topic.setId(doc.getFieldValue("id").toString());
			topic.setUserId(doc.getFieldValue("t_user_id").toString());
			topic.setTopicName(doc.getFieldValue("t_topic_name").toString());
			topic.setTopicDesc(doc.getFieldValue("t_topic_desc").toString());
			topic.setCoverPath(doc.getFieldValue("t_cover_path").toString());
			topic.setParticipationCounts(Long.valueOf(doc.getFieldValue("t_participation_counts").toString()));
			topic.setStatus(Integer.valueOf(doc.getFieldValue("t_status").toString()));
			//topic.setCreateTime(Date.valueOf(String.valueOf(doc.getFieldValue("t_create_time").toString())));
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//topic.setCreateTime(Date.valueOf(doc.getFieldValue("t_create_time").toString()));
			topic.setCreateTime(new Date(simpleDateFormat.parse(doc.getFieldValue("t_create_time").toString()).getTime()));
			//Date date=simpleDateFormat.parse(strDate);
			tlist.add(topic);
		}
		return tlist;
	}
	/**
	 * 查找video
	 * @param key
	 * @param page
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public List<Videos> selectVideoByKey(String key,int page) throws SolrServerException, IOException, ParseException{
		if(key==null)
			key = "";
		List<Videos> vlist = new ArrayList<Videos>();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SolrDocumentList docs = getDocs("v_video_desc:*" + key + "*", page, 10);
		for (SolrDocument doc : docs) {
			Videos v = new Videos();
			
			v.setId(doc.getFieldValue("id").toString());
			v.setUserId(doc.getFieldValue("v_user_id").toString());
			v.setAudioId(doc.getFieldValue("v_audio_id").toString());
			v.setVideoDesc(doc.getFieldValue("v_video_desc").toString());
			v.setVideoPath(doc.getFieldValue("v_video_path").toString());
			v.setVideoSeconds(Float.valueOf(doc.getFieldValue("v_video_seconds").toString()));
			v.setVideoWidth(Integer.valueOf(doc.getFieldValue("v_video_width").toString()));
			v.setVideoHeight(Integer.valueOf(doc.getFieldValue("v_video_height").toString()));
			v.setCoverPath(doc.getFieldValue("v_cover_path").toString());
			v.setLikeCounts(Long.valueOf(doc.getFieldValue("v_like_counts").toString()));
			v.setStatus(Integer.valueOf(doc.getFieldValue("v_status").toString()));
			//v.setCreateTime(Date.valueOf(doc.getFieldValue("v_create_time").toString()));
			v.setCreateTime(new Date(simpleDateFormat.parse(doc.getFieldValue("v_create_time").toString()).getTime()));
			v.setClickCounts(Long.valueOf(doc.getFieldValue("v_click_counts").toString()));
			v.setTopicId(doc.getFieldValue("v_topic_id").toString());
			v.setBgmPosition(Integer.valueOf(doc.getFieldValue("v_bgm_position").toString()));
			
			vlist.add(v);
		}
		return vlist;
	}
	

}
