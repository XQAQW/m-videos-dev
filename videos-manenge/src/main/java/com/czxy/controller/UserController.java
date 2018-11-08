package com.czxy.controller;

import com.czxy.po.Users;
import com.czxy.service.UserService;
import com.czxy.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@Api(value = "用户相关业务的接口", tags = {"用户相关业务的Controller"})
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户上传头像", notes = "用户上传头像的接口")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true,
            dataType = "String")
    @PostMapping("/uploadFace")
    public JSONResult uploadFace(String userId, @RequestParam("file") MultipartFile[] files) {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;

        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("用户id不能为空...");
        }
        //文件保存的命名空间
        String fileSpace = "/usr/local/programing/WeChartProject/video_dev/videos_dev_image";
        //保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/face";
        try {
            //判断依稀如果接收的文件不等于null
            if (files != null && files.length > 0) {

                //获取文件名
                String filename = files[0].getOriginalFilename();
                //获取文件名不能为null
                if (StringUtils.isNotBlank(filename)) {
                    //文件上传的最终保存路径
                    String finalFacePath = fileSpace + uploadPathDB + "/" + filename;
                    //设置数据库保存的路径
                    uploadPathDB += ("/" + filename);
                    //创建一个文件把上面确定的最终路径给他
                    File outFile = new File(finalFacePath);
                    //确定这个文件的父文件夹不能为空 或 这个文件的父文件不能不是文件夹
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    //将这个文件进行输出
                    fileOutputStream = new FileOutputStream(outFile);
                    //获得这个文件的输入
                    inputStream = files[0].getInputStream();
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
        //创建一个User对象
        Users user = new Users();
        //设置用户id
        user.setId(userId);
        //设置用户的头像
        user.setFaceImage(uploadPathDB);
        //更新到数据库中
        userService.updateUserInfo(user);
        return JSONResult.ok();
    }
}
