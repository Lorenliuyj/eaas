package com.newtouch.buglifecycle.dao;

import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.DefectThanFiveVO;
import com.newtouch.common.entity.base.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DefectThanFiveDao {
    List<DefectThanFiveVO> findThan5Bug(@Param("systemDTO") SystemDTO systemDTO,@Param("page")Page page);
}
