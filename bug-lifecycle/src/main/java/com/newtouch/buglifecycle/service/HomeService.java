package com.newtouch.buglifecycle.service;

import com.newtouch.buglifecycle.entity.home.HomeDashBoardVO;

/**
 * @author lgyu
 * @date 2019/2/20 15:29
 */
public interface HomeService {

    int getHomeDashBoardData(String systemName,String version);
}
