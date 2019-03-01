package com.newtouch.buglifecycle.dao;

import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;
import com.newtouch.common.entity.base.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UnsolvedBugDetialDao {

    List<UnsolvedBugDetialVO> findBugDetail(SystemDTO systemVO);

    List<UnsolvedBugDetialVO> findBugDetailPage(@Param("systemVO") SystemDTO systemVO, @Param("page") Page page);
    List<UnsolvedBugDetialVO> findAll(SystemDTO systemVO);
}
