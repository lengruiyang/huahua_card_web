package cn.huiounet.pojo.diandan;

import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.List;

public class ReturnFenLei implements Serializable{
    private String title;
    private int selected;
    private List list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public ReturnFenLei() {
    }

    public ReturnFenLei(String title, int selected, List list) {
        this.title = title;
        this.selected = selected;
        this.list = list;
    }
}
