package com.gzj.healthydiets.entity;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class Food {
    private Integer id;
    private String name;
    private String chef;
    private Integer sales;
    private Integer energy;
    private BigDecimal price;
    private String imgPath="/img/default.jpg";

    public Food() {
    }

    public Food(Integer id, String name, String author, Integer sales, Integer energy, BigDecimal price, String imgPath) {
        this.id = id;
        this.name = name;
        this.chef = author;
        this.sales = sales;
        this.energy = energy;
        this.price = price;
        if(imgPath!=null&&!"".equalsIgnoreCase(imgPath)){
            this.imgPath = imgPath;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales =sales;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        if(imgPath!=null&&!"".equalsIgnoreCase(imgPath)){
            this.imgPath = imgPath;
        }
    }

    @Override
    public String toString() {
        return "food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + chef + '\'' +
                ", sales=" + sales +
                ", energy=" + energy +
                ", price=" + price +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
