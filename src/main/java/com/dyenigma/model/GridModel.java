package com.dyenigma.model;

import java.util.ArrayList;
import java.util.List;

/**
 * topic
 * author: dyenigma
 * create: 2016/4/5 11:53
 */
public class GridModel {
    private List rows = new ArrayList();
    private Integer total = 0;

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
