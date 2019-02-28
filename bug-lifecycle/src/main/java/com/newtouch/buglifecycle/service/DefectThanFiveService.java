package com.newtouch.buglifecycle.service;

import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.DefectThanFiveVO;

import java.util.List;

public interface DefectThanFiveService {
    List<DefectThanFiveVO> findThan5Bug(SystemDTO systemVO);
}
