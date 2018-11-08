package com.czxy.po.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApiModel(value = "用户对象",description = "这是用户对象")
public class UsersVO {
    /**
     * 主键
     */
    private String id;

    private String userToken;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore //加上这个注解password也不会被传出去
    private String password;

    /**
     * 头像
     */
    private String faceImage;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 粉丝数量
     */
    private Integer fansCounts;

    /**
     * 我关注的人总数
     */
    private Integer followCounts;

    /**
     * 我接受到的赞美/收藏 的数量
     */
    private Integer receiveLikeCounts;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

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
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取头像
     *
     * @return face_image - 头像
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * 设置头像
     *
     * @param faceImage 头像
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取粉丝数量
     *
     * @return fans_counts - 粉丝数量
     */
    public Integer getFansCounts() {
        return fansCounts;
    }

    /**
     * 设置粉丝数量
     *
     * @param fansCounts 粉丝数量
     */
    public void setFansCounts(Integer fansCounts) {
        this.fansCounts = fansCounts;
    }

    /**
     * 获取关注
     *
     * @return follow_counts - 关注
     */
    public Integer getFollowCounts() {
        return followCounts;
    }

    /**
     * 设置关注
     *
     * @param followCounts 关注
     */
    public void setFollowCounts(Integer followCounts) {
        this.followCounts = followCounts;
    }

    /**
     * 获取获赞数
     *
     * @return receive_like_counts - 获赞数
     */
    public Integer getReceiveLikeCounts() {
        return receiveLikeCounts;
    }

    /**
     * 设置获赞数
     *
     * @param receiveLikeCounts 获赞数
     */
    public void setReceiveLikeCounts(Integer receiveLikeCounts) {
        this.receiveLikeCounts = receiveLikeCounts;
    }
}