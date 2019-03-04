package com.newtouch.buglifecycle.service.impl;

import com.newtouch.buglifecycle.dao.UnsolvedBugDetialDao;
import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;
import com.newtouch.buglifecycle.service.UnsolvedBugDetialService;
import com.newtouch.common.entity.base.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UnsolvedBugDetialServiceImpl implements UnsolvedBugDetialService {
    @Resource
    UnsolvedBugDetialDao unsolvedBugDetialDao;

    @Override
    public Page<UnsolvedBugDetialVO> findAll(SystemDTO systemVO, Page page) {
        page.init();
        page.setList(unsolvedBugDetialDao.findAll(systemVO,page));
        page.setTotalNum(unsolvedBugDetialDao.findAllTotal(systemVO));
        return page;
    }

    @Override
    public Page<UnsolvedBugDetialVO> findBugDetail(SystemDTO systemVO,Page page) {
        page.init();
        if(!"".equals(systemVO.getStoryId())) {
            page.setList(unsolvedBugDetialDao.findBugDetail(systemVO,page));
            page.setTotalNum(unsolvedBugDetialDao.findBugDetailTotal(systemVO));
        }else {
            page.setList(null);
            page.setTotalNum(0);
        }
        return page;
    }

}
