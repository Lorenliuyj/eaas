package com.newtouch.buglifecycle.entity.home;

import com.newtouch.buglifecycle.entity.component.PieVO;

import java.util.List;

/**
 * @author lgyu
 * @date 2019/2/22 14:01
 */
public class HomePieVO {

    private List<PieVO> PieForSys;

    private List<PieVO> PieForRework;

    private List<PieVO> PieForOver48;

    public List<PieVO> getPieForSys() {
        return PieForSys;
    }

    public void setPieForSys(List<PieVO> pieForSys) {
        PieForSys = pieForSys;
    }

    public List<PieVO> getPieForRework() {
        return PieForRework;
    }

    public void setPieForRework(List<PieVO> pieForRework) {
        PieForRework = pieForRework;
    }

    public List<PieVO> getPieForOver48() {
        return PieForOver48;
    }

    public void setPieForOver48(List<PieVO> pieForOver48) {
        PieForOver48 = pieForOver48;
    }
}
