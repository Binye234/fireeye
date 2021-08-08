package cn.boood.fireeye.vo;

/**
 * @description:
 * @author: boood
 * @time: 2021/8/3 16:03
 */
public class TaskNum {
    private String name="";
    public TaskNum(){}
    public TaskNum(String name, int count) {
        this.name = name;
        this.count = count;
    }

    private int count=0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

