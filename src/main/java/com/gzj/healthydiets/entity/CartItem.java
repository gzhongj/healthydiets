package com.gzj.healthydiets.entity;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 购物车商品对象
 */
@Component
public class CartItem {
    private Integer id;
    private String name;
    private Integer count;
    private BigDecimal singlePrice;
    private BigDecimal totalPrice;

    public CartItem() {
    }

    public CartItem(Integer id, String name, Integer count, BigDecimal singlePrice, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.singlePrice = singlePrice;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return singlePrice.multiply(new BigDecimal(getCount()));
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", singlePrice=" + singlePrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
