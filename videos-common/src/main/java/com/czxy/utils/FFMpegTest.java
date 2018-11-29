package com.czxy.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FFMpegTest {

    private String ffmpegEXE;

    /**
     * 构造函数
     * @param ffmpegEXE
     */
    public FFMpegTest(String ffmpegEXE) {
        this.ffmpegEXE = ffmpegEXE;
    }

    public void convertor(String videoInputPath, String videoOutputPath) throws IOException {
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);

        command.add("-i");
        command.add(videoInputPath);
        command.add(videoOutputPath);

        //java调用命令行的方法 参数:需要执行的命令 (此处命令需要以集合的方式传入)
        ProcessBuilder builder = new ProcessBuilder(command);
        //ProcessBuilder用start方法执行命令
        Process process = builder.start();
        //获取其中的流
        InputStream errorStream = process.getErrorStream();
        //放入字符流
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        //放入高效字符流
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        while ((line = br.readLine()) != null) {
        }

        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }
    }

    public static void main(String[] args) {
        //当前下载的ffmpeg命令所在
        FFMpegTest ffmpeg = new FFMpegTest("/usr/local/programing/ffmpeg/ffmpeg");
        //当前所上传视频的位置 ， 最终生成的视频存放的位置
        try {
            //调用方法
            ffmpeg.convertor("/usr/local/programing/测试视频.mp4","/usr/local/programing/生成视频.avi");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
