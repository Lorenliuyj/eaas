package com.newtouch.buglifecycle.dao;

import com.newtouch.buglifecycle.entity.SystemVO;
import com.newtouch.buglifecycle.entity.UnsolvedBugDetialVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UnsolvedBugDetialDao {

    List<UnsolvedBugDetialVO> findBugDetail(SystemVO systemVO);

}
