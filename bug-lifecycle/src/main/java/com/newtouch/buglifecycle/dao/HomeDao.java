package com.newtouch.buglifecycle.dao;

import org.apache.ibatis.annotations.Param;


/**
 * @author lgyu
 * @date 2019/2/20 16:06
 */

public interface HomeDao {

    int getRedevBugCount(@Param("systemName") String systemName, @Param("version")String version);

//    int get24UnsolvedBugCount(@Param("systemName") String systemName, @Param("version")String version);
//
//    int get48UnsolvedBugCount(@Param("systemName") String systemName, @Param("version")String version);

}
