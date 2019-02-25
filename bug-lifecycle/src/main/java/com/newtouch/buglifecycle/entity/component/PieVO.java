package com.newtouch.buglifecycle.entity.component;

/**
 * 饼图VO
 * @author lgyu
 * @date 2019/2/22 13:46
 */
public class PieVO {

    private int value;

    private String name;

    private String key;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
