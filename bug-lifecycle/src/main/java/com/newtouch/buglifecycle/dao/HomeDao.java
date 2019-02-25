package com.newtouch.buglifecycle.dao;

import com.newtouch.buglifecycle.entity.component.ComboxVO;
import com.newtouch.buglifecycle.entity.component.PieVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author lgyu
 * @date 2019/2/20 16:06
 */
public interface HomeDao {

    List<ComboxVO> getSystemList();

    List<ComboxVO> getVersionList();

    int getRedevBugCount(@Param("systemName") String systemName,@Param("version") String version,@Param("status") String status);

    int get24BugCount(@Param("systemName") String systemName, @Param("version") String version, @Param("status") String status);

    int get48BugCount(@Param("systemName") String systemName, @Param("version") String version, @Param("status") String status);

    List<PieVO> getBugBySystem(@Param("systemName") String systemName, @Param("version") String version, @Param("status") String status);

    List<PieVO> getBugByRedev(@Param("systemName") String systemName, @Param("version") String version, @Param("status") String status);

    List<PieVO> getBugByOver48(@Param("systemName") String systemName, @Param("version") String version, @Param("status") String status);

    List<PieVO> getBugByHour(@Param("systemName") String systemName, @Param("version") String version, @Param("status") String status);
}
