package com.newtouch.buglifecycle.service;

import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;

import java.util.List;

public interface UnsolvedBugDetialService {

    List<UnsolvedBugDetialVO> findAll(SystemDTO systemVO);
    List<UnsolvedBugDetialVO> findBugDetail(SystemDTO systemVO);

}
