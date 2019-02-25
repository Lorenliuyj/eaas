package com.newtouch.buglifecycle.service;

import com.newtouch.buglifecycle.entity.SystemVO;
import com.newtouch.buglifecycle.entity.UnsolvedBugDetialVO;

import java.util.List;

public interface UnsolvedBugDetialService {

    List<UnsolvedBugDetialVO> findAll(SystemVO systemVO);
    List<UnsolvedBugDetialVO> findBugDetail(SystemVO systemVO);

}
