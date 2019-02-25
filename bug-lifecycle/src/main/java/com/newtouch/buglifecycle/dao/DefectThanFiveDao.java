package com.newtouch.buglifecycle.dao;

import com.newtouch.buglifecycle.entity.DefectThanFiveVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DefectThanFiveDao {
    List<DefectThanFiveVO> findThan5Bug(@Param("system") String system, @Param("version") String version, @Param("unDeal") boolean unDeal);
}
