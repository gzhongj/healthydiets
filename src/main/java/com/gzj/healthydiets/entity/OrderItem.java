package com.gzj.healthydiets.entity;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class OrderItem {
    private Integer id;
    private Integer itemId;
    private String orderId;
    private String name;
    private Integer count;
    private BigDecimal singlePrice;
    private BigDecimal totalPrice;

    public OrderItem() {
    }

    public OrderItem(Integer id, Integer itemId, String orderId, String name, Integer count, BigDecimal singlePrice, BigDecimal totalPrice) {
        this.id = id;
        this.itemId = itemId;
        this.orderId = orderId;
        this.name = name;
        this.count = count;
        this.singlePrice = singlePrice;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(BigDecimal singlePrice) {
        this.singlePrice = singlePrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", orderId='" + orderId + '\'' +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", singlePrice=" + singlePrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
