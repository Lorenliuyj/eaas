package com.newtouch.buglifecycle.service.impl;

import com.newtouch.buglifecycle.dao.DefectThanFiveDao;
import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.DefectThanFiveVO;
import com.newtouch.buglifecycle.service.DefectThanFiveService;
import com.newtouch.common.entity.base.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefectThanFiveServiceImpl implements DefectThanFiveService {

    @Resource
    DefectThanFiveDao defectThanFiveDao;

    @Override
    public Page<DefectThanFiveVO> findThan5Bug(SystemDTO systemVO, Page page) {
        page.init();
        page.setList(defectThanFiveDao.findThan5Bug(systemVO,page));
        return page;
    }

}
