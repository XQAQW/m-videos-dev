package com.czxy.po;

import java.util.Date;
import javax.persistence.*;

public class Videos {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 音频id
     */
    @Column(name = "audio_id")
    private String audioId;

    /**
     * 音频描述
     */
    @Column(name = "video_desc")
    private String videoDesc;

    /**
     * 音频路径
     */
    @Column(name = "video_path")
    private String videoPath;

    /**
     * 音频时长
     */
    @Column(name = "video_seconds")
    private Float videoSeconds;

    /**
     * 视频的宽
     */
    @Column(name = "video_width")
    private Integer videoWidth;

    /**
     * 视频的高
     */
    @Column(name = "video_height")
    private Integer videoHeight;

    /**
     * 封面路径
     */
    @Column(name = "cover_path")
    private String coverPath;

    /**
     * 获得喜欢的数量
     */
    @Column(name = "like_counts")
    private Long likeCounts;

    /**
     * 视频状态：1、发布成功2、禁止播放，管理员操作
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取音频id
     *
     * @return audio_id - 音频id
     */
    public String getAudioId() {
        return audioId;
    }

    /**
     * 设置音频id
     *
     * @param audioId 音频id
     */
    public void setAudioId(String audioId) {
        this.audioId = audioId;
    }

    /**
     * 获取音频描述
     *
     * @return video_desc - 音频描述
     */
    public String getVideoDesc() {
        return videoDesc;
    }

    /**
     * 设置音频描述
     *
     * @param videoDesc 音频描述
     */
    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    /**
     * 获取音频路径
     *
     * @return video_path - 音频路径
     */
    public String getVideoPath() {
        return videoPath;
    }

    /**
     * 设置音频路径
     *
     * @param videoPath 音频路径
     */
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    /**
     * 获取音频时长
     *
     * @return video_seconds - 音频时长
     */
    public Float getVideoSeconds() {
        return videoSeconds;
    }

    /**
     * 设置音频时长
     *
     * @param videoSeconds 音频时长
     */
    public void setVideoSeconds(Float videoSeconds) {
        this.videoSeconds = videoSeconds;
    }

    /**
     * 获取视频的宽
     *
     * @return video_width - 视频的宽
     */
    public Integer getVideoWidth() {
        return videoWidth;
    }

    /**
     * 设置视频的宽
     *
     * @param videoWidth 视频的宽
     */
    public void setVideoWidth(Integer videoWidth) {
        this.videoWidth = videoWidth;
    }

    /**
     * 获取视频的高
     *
     * @return video_height - 视频的高
     */
    public Integer getVideoHeight() {
        return videoHeight;
    }

    /**
     * 设置视频的高
     *
     * @param videoHeight 视频的高
     */
    public void setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    /**
     * 获取封面路径
     *
     * @return cover_path - 封面路径
     */
    public String getCoverPath() {
        return coverPath;
    }

    /**
     * 设置封面路径
     *
     * @param coverPath 封面路径
     */
    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    /**
     * 获取获得喜欢的数量
     *
     * @return like_counts - 获得喜欢的数量
     */
    public Long getLikeCounts() {
        return likeCounts;
    }

    /**
     * 设置获得喜欢的数量
     *
     * @param likeCounts 获得喜欢的数量
     */
    public void setLikeCounts(Long likeCounts) {
        this.likeCounts = likeCounts;
    }

    /**
     * 获取视频状态：1、发布成功2、禁止播放，管理员操作
     *
     * @return status - 视频状态：1、发布成功2、禁止播放，管理员操作
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置视频状态：1、发布成功2、禁止播放，管理员操作
     *
     * @param status 视频状态：1、发布成功2、禁止播放，管理员操作
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}