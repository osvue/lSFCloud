package com.bz.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: THE GIFTED
 * @CreateTime 2019年07月31日 15:56
 */
public class Commons {

    private Integer page;
    private Integer rows;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;
    @Override
    public String toString() {
        return "Commons{" +
                "page=" + page +
                ", rows=" + rows +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date begin;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
