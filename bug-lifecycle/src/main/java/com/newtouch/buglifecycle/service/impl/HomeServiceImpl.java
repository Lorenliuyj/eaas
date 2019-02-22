package com.newtouch.buglifecycle.service.impl;

import com.newtouch.buglifecycle.dao.HomeDao;
import com.newtouch.buglifecycle.entity.home.HomeDashBoardVO;
import com.newtouch.buglifecycle.service.HomeService;

import javax.annotation.Resource;

/**
 * @author lgyu
 * @date 2019/2/20 16:01
 */
public class HomeServiceImpl implements HomeService {

    @Resource
    HomeDao homeDao;

    @Override
    public int getHomeDashBoardData(String systemName, String version) {
        return homeDao.getRedevBugCount(systemName, version);
    }
}
