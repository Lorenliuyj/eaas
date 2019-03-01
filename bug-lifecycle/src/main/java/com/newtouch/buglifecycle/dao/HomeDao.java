package com.newtouch.buglifecycle.dao;

import com.newtouch.buglifecycle.entity.base.SystemDTO;
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

    int getRedevBugCount(SystemDTO systemDTO);

    int get24BugCount(SystemDTO systemDTO);

    int get48BugCount(SystemDTO systemDTO);

    List<PieVO> getBugBySystem(SystemDTO systemDTO);

    List<PieVO> getBugByRedev(SystemDTO systemDTO);

    List<PieVO> getBugByOver48(SystemDTO systemDTO);

    List<PieVO> getBugByHour(SystemDTO systemDTO);
}
