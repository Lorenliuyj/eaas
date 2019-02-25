package com.newtouch.buglifecycle.service.impl;

import com.newtouch.buglifecycle.dao.HomeDao;
import com.newtouch.buglifecycle.entity.BugsInfoVO;
import com.newtouch.buglifecycle.entity.SystemVO;
import com.newtouch.buglifecycle.service.HomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lgyu
 * @date 2019/2/20 16:01
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    HomeDao homeDao;

    @Override
    public int getHomeDashBoardData(String systemName, String version) {
        return homeDao.getRedevBugCount(systemName, version);
    }

}
