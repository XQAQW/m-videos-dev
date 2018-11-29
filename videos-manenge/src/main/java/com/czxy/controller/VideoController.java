package com.czxy.controller;

import com.czxy.enums.VideoStatusEnum;
import com.czxy.po.Bgm;
import com.czxy.po.Videos;
import com.czxy.service.BgmService;
import com.czxy.service.VideoService;
import com.czxy.utils.JSONResult;
import com.czxy.utils.MergeVideoMp3;
import io.swagger.annotations.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @author M(xqwQAQwq @ 163.com)
 * @desc <p>videoController
 * @date 2018/11/29 4:45 PM
 */
@RestController
@Api(value = "视频相关业务的接口", tags = {"视频相关业务的controller"})
@RequestMapping("/video")
public class VideoController extends BasicController {

    @Autowired
    private BgmService bgmService;

    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "上传视频", notes = "上传视频的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "bgmId", value = "背景音乐id", required = false, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "videoSeconds", value = "背景音乐播放长度", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "videoWidth", value = "视频宽度", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "videoHeight", value = "视频高度", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "desc", value = "视频描述", required = false, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    public JSONResult upload(String userId,
                             String bgmId, double videoSeconds, int videoWidth, int videoHight,
                             String desc,
                             @ApiParam(value = "短视频", required = true)
                                     MultipartFile file) throws IOException {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;

        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("用户id不能为空...");
        }
        //文件保存的命名空间
//        String fileSpace = "/usr/local/programing/WeChartProject/video_dev/videos_dev_image";
        //保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/video";
        String finalvideoPath = "";
        try {
            //判断依稀如果接收的文件不等于null
            if (file != null) {

                //获取文件名
                String filename = file.getOriginalFilename();
                //获取文件名不能为null
                if (StringUtils.isNotBlank(filename)) {
                    //文件上传的最终保存路径
                    finalvideoPath = FILE_SPACE + uploadPathDB + "/" + filename;
                    //设置数据库保存的路径
                    uploadPathDB += ("/" + filename);
                    //创建一个文件把上面确定的最终路径给他
                    File outFile = new File(finalvideoPath);
                    //确定这个文件的父文件夹不能为空 或 这个文件的父文件不能不是文件夹
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    //将这个文件进行输出
                    fileOutputStream = new FileOutputStream(outFile);
                    //获得这个文件的输入
                    inputStream = file.getInputStream();
                    //使用工具进行copy
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } else {
                return JSONResult.errorMsg("上传出错!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("上传错误，请重新上传");
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        //判断bgmId是否为空，如果不为空
        //那就查询bgm信息，并且合并视频，生产新的视频
        if (StringUtils.isNotBlank(bgmId)) {
            Bgm bgm = bgmService.queryBgmById(bgmId);
            String mp3InputPath = FILE_SPACE + bgm.getPath();

            MergeVideoMp3 tool = new MergeVideoMp3(FFMPEG_EXE);
            String videoInputPath = finalvideoPath;

            String videoOutputName = UUID.randomUUID().toString() + ".mp4";
            uploadPathDB = "/" + userId + "/video" + "/" + videoOutputName;
            finalvideoPath = FILE_SPACE + uploadPathDB;
            tool.convertor(videoInputPath, mp3InputPath, videoSeconds, finalvideoPath);
        }

        // 保存视频信息到数据库
        Videos video = new Videos();
        // 设置bgmid（此处bgmid可以为空，有值则说明他选择了bgm，无值则说明他没有选择）
        video.setAudioId(bgmId);
        // 设置来自用户id
        video.setUserId(userId);
        // 设置视频大小
        video.setVideoSeconds((float) videoSeconds);
        // 设置视频的宽
        video.setVideoWidth(videoWidth);
        // 设置视频的高
        video.setVideoHeight(videoHight);
        // 设置视频的描述
        video.setVideoDesc(desc);
        // 设置视频的上传路径
        video.setVideoPath(uploadPathDB);
        // 设置状态
        video.setStatus(VideoStatusEnum.SUCCESS.value);
        // 设置创建时间
        video.setCreateTime(new Date());
        // 调用save方法保存
        videoService.saveVideo(video);
        //返回
        return JSONResult.ok();
    }
}
