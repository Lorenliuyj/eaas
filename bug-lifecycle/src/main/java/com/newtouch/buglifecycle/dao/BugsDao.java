package com.newtouch.buglifecycle.dao;

import com.newtouch.buglifecycle.entity.home.BugsInfoVO;
import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;
import com.newtouch.common.entity.base.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author lgyu
 * @date 2019/2/20 16:06
 */
public interface BugsDao {

    /**
     * 48小时未解决的bug
     * @param systemVO
     * @return
     */
    List<BugsInfoVO> tableFor48UnDeal(SystemDTO systemVO);


    /**
     * bug数排名前十的
     * @param systemVO
     * @return
     */
    List<BugsInfoVO> tableForRank10(SystemDTO systemVO);


    List<UnsolvedBugDetialVO> tableFor48UnDealDetail(@Param("systemDTO")SystemDTO systemDTO,  @Param("page")Page page);

    int tableFor48UnDealCount(@Param("systemDTO")SystemDTO systemDTO);

    List<UnsolvedBugDetialVO> tableForRank10Detail(@Param("systemDTO")SystemDTO systemDTO,  @Param("page")Page page);

    int tableForRank10Count(@Param("systemDTO")SystemDTO systemDTO);

    List findAllUnsolvedBug(@Param("systemDTO")SystemDTO systemDTO,  @Param("page")Page page);

    int findAllUnsolvedBugTotal(@Param("systemDTO")SystemDTO systemVO);

    List findBugDetail(@Param("systemDTO")SystemDTO systemDTO,  @Param("page")Page page);

    int findBugDetailTotal(@Param("systemDTO")SystemDTO systemVO);

    List findThan5Bug(@Param("systemDTO")SystemDTO systemDTO,  @Param("page")Page page);

    int findThan5BugTotal(@Param("systemDTO")SystemDTO systemDTO);
}
