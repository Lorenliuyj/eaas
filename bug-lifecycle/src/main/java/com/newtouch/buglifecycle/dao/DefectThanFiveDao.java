package com.newtouch.buglifecycle.dao;

import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.DefectThanFiveVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DefectThanFiveDao {
    List<DefectThanFiveVO> findThan5Bug(SystemDTO systemVO);
}
