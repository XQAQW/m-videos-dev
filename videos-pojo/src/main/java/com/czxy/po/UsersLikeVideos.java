package com.czxy.po;

import javax.persistence.*;

@Table(name = "users_like_videos")
public class UsersLikeVideos {
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
    @Column(name = "video_id")
    private String videoId;

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
     * @return video_id - 音频id
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * 设置音频id
     *
     * @param videoId 音频id
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}