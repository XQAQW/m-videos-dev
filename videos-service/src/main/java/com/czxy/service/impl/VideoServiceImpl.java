package com.czxy.service.impl;

import com.czxy.mapper.VideosMapper;
import com.czxy.po.Videos;
import com.czxy.service.VideoService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
  *
  * @author M(xqwQAQwq@163.com)
  *
  * @desc <p>
  * @date 2018/11/29 4:22 PM
  */
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideosMapper videosMapper ;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveVideo(Videos video) {
        // 生成主键
        String id = sid.nextShort();
        // 设置主键
        video.setId(id);
        // 此处我们使用insertSelective()这个方法会保存一个实体，null的属性不会保存，会使用数据库默认值
        videosMapper.insertSelective(video);
    }
}
