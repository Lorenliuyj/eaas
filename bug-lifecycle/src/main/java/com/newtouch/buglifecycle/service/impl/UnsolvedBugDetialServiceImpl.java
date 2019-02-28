package com.newtouch.buglifecycle.service.impl;

import com.newtouch.buglifecycle.dao.UnsolvedBugDetialDao;
import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;
import com.newtouch.buglifecycle.service.UnsolvedBugDetialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UnsolvedBugDetialServiceImpl implements UnsolvedBugDetialService {
    @Resource
    UnsolvedBugDetialDao unsolvedBugDetialDao;

    @Override
    public List<UnsolvedBugDetialVO> findAll(SystemDTO systemVO) {
        return unsolvedBugDetialDao.findAll(systemVO);
    }

    @Override
    public List<UnsolvedBugDetialVO> findBugDetail(SystemDTO systemVO) {
        return unsolvedBugDetialDao.findBugDetail(systemVO);
    }

}
