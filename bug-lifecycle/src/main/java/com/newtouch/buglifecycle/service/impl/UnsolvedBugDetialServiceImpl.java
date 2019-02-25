package com.newtouch.buglifecycle.service.impl;

import com.newtouch.buglifecycle.dao.UnsolvedBugDetialDao;
import com.newtouch.buglifecycle.entity.SystemVO;
import com.newtouch.buglifecycle.entity.UnsolvedBugDetialVO;
import com.newtouch.buglifecycle.service.UnsolvedBugDetialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UnsolvedBugDetialServiceImpl implements UnsolvedBugDetialService {
    @Resource
    UnsolvedBugDetialDao unsolvedBugDetialDao;

    @Override
    public List<UnsolvedBugDetialVO> findAll(SystemVO systemVO) {
        return unsolvedBugDetialDao.findBugDetail(systemVO);
    }

    @Override
    public List<UnsolvedBugDetialVO> findBugDetail(SystemVO systemVO) {
        return unsolvedBugDetialDao.findBugDetail(systemVO);
    }

}
