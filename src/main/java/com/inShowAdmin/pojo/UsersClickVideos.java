package com.inShowAdmin.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`users_click_videos`")
public class UsersClickVideos implements Serializable {
	@Id
    private String id;

    private String userId;

    private String videoId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

	@Override
	public String toString() {
		return "UsersClickVideos [id=" + id + ", userId=" + userId + ", videoId=" + videoId + "]";
	}
}