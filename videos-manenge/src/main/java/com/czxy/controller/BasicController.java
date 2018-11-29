package com.czxy.controller;

import com.czxy.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @Autowired
    public RedisOperator redis;

    public static final String USER_REDIS_SESSION = "user-redis-session";

    /**
     * 文件保存的命名空间
     */
    public static final String FILE_SPACE = "/usr/local/programing/WeChartProject/video_dev/videos_dev_image";

    /**
     * ffmpeg所在目录
     */
    public static final String FFMPEG_EXE = "/usr/local/programing/ffmpeg/ffmpeg";
}
