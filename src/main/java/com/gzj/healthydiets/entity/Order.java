package com.gzj.healthydiets.entity;


import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 */
@Component
public class Order {
    private Integer id;
    private String orderId;
    private Integer userId;
    private Date createTime;
    private BigDecimal price;
    //0:未发货 1：已发货 2：已签收
    private Integer status;

    public Order() {
    }

    public Order(Integer id, String orderId, Integer userId, Date createTime, BigDecimal price, Integer status) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
