package com.newtouch.buglifecycle.service.impl;

import com.newtouch.buglifecycle.dao.DefectThanFiveDao;
import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.DefectThanFiveVO;
import com.newtouch.buglifecycle.service.DefectThanFiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefectThanFiveServiceImpl implements DefectThanFiveService {

    @Resource
    DefectThanFiveDao defectThanFiveDao;

    @Override
    public List<DefectThanFiveVO> findThan5Bug(SystemDTO systemVO) {
        return defectThanFiveDao.findThan5Bug(systemVO);
    }

}
