package com.gzj.healthydiets.entity;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车对象
 */
@Component
public class Cart {
    private Integer itemTotalCount;
    private BigDecimal itemTotalPrice;
    private Map<Integer,CartItem> items=new HashMap<>();

    public Integer getItemTotalCount() {
        Integer itemTotalCount=0;
        for (Map.Entry<Integer,CartItem> entry :items.entrySet()){
            itemTotalCount+=entry.getValue().getCount();
        }
        return itemTotalCount;
    }

    public BigDecimal getItemTotalPrice() {
        BigDecimal itemTotalPrice=new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> entry :items.entrySet()){
            BigDecimal add = itemTotalPrice.add(entry.getValue().getTotalPrice());
            itemTotalPrice=add;
        }
        return itemTotalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "itemTotalCount=" + getItemTotalCount() +
                ", itemTotalPrice=" + getItemTotalPrice() +
                ", items=" + items +
                '}';
    }
    //功能1：添加商品
    public void addCartItem(CartItem cartItem){
        //1、通过cartItem的id从items中获取该cartItem
        CartItem cartItem1 = items.get(cartItem.getId());
        //2、判断获取的cartItem是否为空，以确定items中是否已经存在此商品
        if (cartItem1!=null){
            //2.1 该商品已存在，则只需更改该商品的数量、商品总价
            cartItem1.setCount(cartItem1.getCount()+1);
            cartItem1.setTotalPrice(cartItem1.getSinglePrice().multiply(new BigDecimal(cartItem1.getCount()+1)));
            items.put(cartItem.getId(),cartItem1);
        }else {
            items.put(cartItem.getId(),cartItem);
        }
    }
    //功能2：删除商品
    public void deleteCartItem(Integer id){
        items.remove(id);
    }
    //功能3：清空购物车
    public void clear(){
        items.clear();
    }
    //功能4：修改商品数量
    public void updateCartItemCount(Integer id,Integer count){
        CartItem cartItem = items.get(id);
        cartItem.setCount(count);
        items.put(cartItem.getId(),cartItem);
    }
}
