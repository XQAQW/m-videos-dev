package com.czxy.po;

import java.util.Date;
import javax.persistence.*;

public class Comments {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 音频id
     */
    @Column(name = "video_id")
    private String videoId;

    /**
     * 用户id
     */
    @Column(name = "from_user_id")
    private String fromUserId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 留言内容
     */
    private String comment;

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

    /**
     * 获取用户id
     *
     * @return from_user_id - 用户id
     */
    public String getFromUserId() {
        return fromUserId;
    }

    /**
     * 设置用户id
     *
     * @param fromUserId 用户id
     */
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
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

    /**
     * 获取留言内容
     *
     * @return comment - 留言内容
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置留言内容
     *
     * @param comment 留言内容
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}