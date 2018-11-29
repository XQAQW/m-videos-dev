package com.czxy.service.impl;

import com.czxy.mapper.BgmMapper;
import com.czxy.po.Bgm;
import com.czxy.service.BgmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BgmServiceImpl implements BgmService {

    @Autowired
    private BgmMapper bgmMapper ;

    /**
     * 查询所有的bgm
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Bgm> queryBgmList() {
        return bgmMapper.selectAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Bgm queryBgmById(String bgm) {
        return bgmMapper.selectByPrimaryKey(bgm);
    }
}
