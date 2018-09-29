package com.codermi.common.base.utils;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/9/29 15:47
 * @desc
 */
public class PageVo<T> {

    /**
     * 总页数
     */
    private int pageCount;

    /**
     * 当前页码
     */
    private int pageNum = 1;

    /**
     * 每页显示条数
     */
    private int pageSize = 15;

    private List<T> data;

    /**
     * 总条数
     */
    private long total = 0L;

    /**
     * 偏移值
     */
    private int start;

    public PageVo(){}

    public PageVo(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageVo(int pageNum, int pageSize, int total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }


    public int getPageCount() {
        return pageCount = (int) (total % pageSize == 0 ? total / pageSize : (total / pageSize) + 1);
    }


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getStart() {
        return start = (pageNum - 1) * pageSize;
    }

}
