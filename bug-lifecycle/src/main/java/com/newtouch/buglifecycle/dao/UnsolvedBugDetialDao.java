package com.newtouch.buglifecycle.dao;

import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;

import java.util.List;

public interface UnsolvedBugDetialDao {

    List<UnsolvedBugDetialVO> findBugDetail(SystemDTO systemVO);

}
