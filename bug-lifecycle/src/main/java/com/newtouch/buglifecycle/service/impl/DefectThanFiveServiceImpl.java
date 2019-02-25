package com.newtouch.buglifecycle.service.impl;

import com.newtouch.buglifecycle.dao.DefectThanFiveDao;
import com.newtouch.buglifecycle.entity.DefectThanFiveVO;
import com.newtouch.buglifecycle.service.DefectThanFiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefectThanFiveServiceImpl implements DefectThanFiveService {

    @Resource
    DefectThanFiveDao defectThanFiveDao;

    @Override
    public List<DefectThanFiveVO> findThan5Bug(String system, String version, boolean unDeal) {
        return defectThanFiveDao.findThan5Bug(system,version,unDeal);
    }

}
