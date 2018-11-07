package com.czxy.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "users_report")
public class UsersReport {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 被举报的用户id
     */
    @Column(name = "deal_user_id")
    private String dealUserId;

    /**
     * 被举报音频的id
     */
    @Column(name = "deal_video_id")
    private Integer dealVideoId;

    /**
     * 举报原因
     */
    private String title;

    /**
     * 描述内容
     */
    private String content;

    /**
     * 举报人id
     */
    private String userid;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

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
     * 获取被举报的用户id
     *
     * @return deal_user_id - 被举报的用户id
     */
    public String getDealUserId() {
        return dealUserId;
    }

    /**
     * 设置被举报的用户id
     *
     * @param dealUserId 被举报的用户id
     */
    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId;
    }

    /**
     * 获取被举报音频的id
     *
     * @return deal_video_id - 被举报音频的id
     */
    public Integer getDealVideoId() {
        return dealVideoId;
    }

    /**
     * 设置被举报音频的id
     *
     * @param dealVideoId 被举报音频的id
     */
    public void setDealVideoId(Integer dealVideoId) {
        this.dealVideoId = dealVideoId;
    }

    /**
     * 获取举报原因
     *
     * @return title - 举报原因
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置举报原因
     *
     * @param title 举报原因
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取描述内容
     *
     * @return content - 描述内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置描述内容
     *
     * @param content 描述内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取举报人id
     *
     * @return userid - 举报人id
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置举报人id
     *
     * @param userid 举报人id
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}