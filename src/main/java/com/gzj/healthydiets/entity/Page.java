package com.gzj.healthydiets.entity;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Page<T> {
//1、pageNo           当前页码数            当前页码由客户端传递
//2、pageTotal        总页码数              总页码数=pageTotalCount/pageSize+pageTotalCount%pageSize
//3、pageTotalCount   总记录数              sql语句：select count（*） from 表名；
//4、pageSize         每页显示的记录数       ①客户端传递 ②页面布局决定
//5、items            每页显示的内容         sql语句：select * from 表名 limit （pageNo-1）*pageSize，pageSize
//6、url              分页跳转的地址

    public static final Integer PAGE_SIZE = 4;
    public static final Integer PAGE_NO = 1;

    private Integer pageNo=PAGE_NO;
    private Integer pageSize = PAGE_SIZE;
    private Integer pageTotal;
    private Integer pageTotalCount;
    private String url;
    private List<T> items;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", url='" + url + '\'' +
                ", items=" + items +
                '}';
    }
}
