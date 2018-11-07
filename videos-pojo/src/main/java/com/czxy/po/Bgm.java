package com.czxy.po;

import javax.persistence.*;

public class Bgm {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 作者
     */
    private String author;

    /**
     * 歌曲名
     */
    private String name;

    /**
     * 播放地址
     */
    private String path;

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
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取歌曲名
     *
     * @return name - 歌曲名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置歌曲名
     *
     * @param name 歌曲名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取播放地址
     *
     * @return path - 播放地址
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置播放地址
     *
     * @param path 播放地址
     */
    public void setPath(String path) {
        this.path = path;
    }
}