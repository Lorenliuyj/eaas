package com.newtouch.buglifecycle.dao;

import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;
import com.newtouch.common.entity.base.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UnsolvedBugDetialDao {

    List<UnsolvedBugDetialVO> findBugDetail(@Param("systemDTO") SystemDTO systemDTO,@Param("page")Page page);

    List<UnsolvedBugDetialVO> findBugDetailPage(@Param("systemDTO") SystemDTO systemDTO, @Param("page") Page page);
    List<UnsolvedBugDetialVO> findAll(@Param("systemDTO") SystemDTO systemDTO,@Param("page")Page page);
}
