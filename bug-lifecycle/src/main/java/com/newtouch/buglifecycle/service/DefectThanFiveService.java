package com.newtouch.buglifecycle.service;

import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.DefectThanFiveVO;
import com.newtouch.common.entity.base.Page;

import java.util.List;

public interface DefectThanFiveService {
    Page<DefectThanFiveVO> findThan5Bug(SystemDTO systemVO, Page page);
}
