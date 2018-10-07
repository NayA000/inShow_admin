package com.inShowAdmin.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`videos`")
public class Videos implements Serializable {
	@Override
	public String toString() {
		return "Videos [id=" + id + ", userId=" + userId + ", audioId=" + audioId + ", videoDesc=" + videoDesc
				+ ", videoPath=" + videoPath + ", videoSeconds=" + videoSeconds + ", videoWidth=" + videoWidth
				+ ", videoHeight=" + videoHeight + ", coverPath=" + coverPath + ", likeCounts=" + likeCounts
				+ ", status=" + status + ", createTime=" + createTime + ", clickCounts=" + clickCounts + ", topicId="
				+ topicId + ", bgmPosition=" + bgmPosition + "]";
	}

	@Id
    private String id;

    private String userId;

    private String audioId;

    private String videoDesc;

    private String videoPath;

    private Float videoSeconds;

    private Integer videoWidth;

    private Integer videoHeight;

    private String coverPath;

    private Long likeCounts;

    private Integer status;

    private Date createTime;

    private Long clickCounts;

    private String topicId;

    private Integer bgmPosition;

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

    public String getAudioId() {
        return audioId;
    }

    public void setAudioId(String audioId) {
        this.audioId = audioId;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Float getVideoSeconds() {
        return videoSeconds;
    }

    public void setVideoSeconds(Float videoSeconds) {
        this.videoSeconds = videoSeconds;
    }

    public Integer getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(Integer videoWidth) {
        this.videoWidth = videoWidth;
    }

    public Integer getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public Long getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(Long likeCounts) {
        this.likeCounts = likeCounts;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getClickCounts() {
        return clickCounts;
    }

    public void setClickCounts(Long clickCounts) {
        this.clickCounts = clickCounts;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public Integer getBgmPosition() {
        return bgmPosition;
    }

    public void setBgmPosition(Integer bgmPosition) {
        this.bgmPosition = bgmPosition;
    }
}