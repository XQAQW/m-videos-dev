package com.czxy.service;

import com.czxy.po.Bgm;

import java.util.List;

public interface BgmService {

    /**
     * 查询背景音乐列表
     * @return
     */
    List<Bgm> queryBgmList();

    /**
     * 根据id查询bgm信息
     *
     * @param bgm
     * @return
     */
    Bgm queryBgmById(String bgm);
}
