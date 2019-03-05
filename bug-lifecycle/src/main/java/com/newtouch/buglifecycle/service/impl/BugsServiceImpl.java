package com.newtouch.buglifecycle.service.impl;

import com.newtouch.buglifecycle.dao.BugsDao;
import com.newtouch.buglifecycle.entity.home.BugsInfoVO;
import com.newtouch.buglifecycle.entity.base.SystemDTO;
import com.newtouch.buglifecycle.entity.home.DefectThanFiveVO;
import com.newtouch.buglifecycle.entity.home.UnsolvedBugDetialVO;
import com.newtouch.buglifecycle.service.BugsService;
import com.newtouch.common.entity.base.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class BugsServiceImpl implements BugsService {

    @Resource
    BugsDao bugsDao;
    @Override
    public List<BugsInfoVO> tableFor48UnDeal(SystemDTO systemVO) {
        return bugsDao.tableFor48UnDeal(systemVO);
    }

    @Override
    public List<BugsInfoVO> tableForRank10(SystemDTO systemVO) {
        return bugsDao.tableForRank10(systemVO);
    }

    @Override
    public Page<UnsolvedBugDetialVO> tableFor48UnDealDetail(SystemDTO systemDTO, Page page) {
        page.init();
        page.setList(bugsDao.tableFor48UnDealDetail(systemDTO,page));
        page.setTotalNum(bugsDao.tableFor48UnDealCount(systemDTO));
        return page;
    }

    @Override
    public Page<UnsolvedBugDetialVO> tableForRank10Detail(SystemDTO systemVO, Page page) {
        page.init();
        page.setList(bugsDao.tableForRank10Detail(systemVO,page));
        page.setTotalNum(bugsDao.tableForRank10Count(systemVO));
        return page;
    }

    @Override
    public Page<UnsolvedBugDetialVO> findAllUnsolvedBug(SystemDTO systemVO, Page page) {
        page.init();
        page.setList(bugsDao.findAllUnsolvedBug(systemVO,page));
        page.setTotalNum(bugsDao.findAllUnsolvedBugTotal(systemVO));
        return page;
    }

    @Override
    public Page<UnsolvedBugDetialVO> findBugDetail(SystemDTO systemVO,Page page) {
        page.init();
        page.setList(bugsDao.findBugDetail(systemVO,page));
        page.setTotalNum(bugsDao.findBugDetailTotal(systemVO));
        return page;
    }

    @Override
    public Page<DefectThanFiveVO> findThan5Bug(SystemDTO systemDTO, Page page) {
        page.init();
        page.setList(bugsDao.findThan5Bug(systemDTO,page));
        page.setTotalNum(bugsDao.findThan5BugTotal(systemDTO));
        return page;
    }
}
