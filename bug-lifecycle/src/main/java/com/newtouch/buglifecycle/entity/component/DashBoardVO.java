package com.newtouch.buglifecycle.entity.component;

/**
 * 仪表盘VO
 * @author lgyu
 * @date 2019/2/20 11:15
 */
public class DashBoardVO {

    /**
     * 安全值
     */
    private int action;
    /**
     * 橙色预警值
     */
    private int warnning;
    /**
     * 红色预警值
     */
    private int max;
    /**
     * 当前数值
     */
    private int currentValue;

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getWarnning() {
        return warnning;
    }

    public void setWarnning(int warnning) {
        this.warnning = warnning;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
