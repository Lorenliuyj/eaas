package com.newtouch.buglifecycle.service;

import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;
import com.newtouch.common.entity.base.Page;

import java.util.List;

public interface UnsolvedBugDetialService {

    Page<UnsolvedBugDetialVO> findAll(SystemDTO systemVO, Page page);
    Page<UnsolvedBugDetialVO> findBugDetail(SystemDTO systemVO,Page page);

}
