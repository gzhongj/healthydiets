package com.gzj.healthydiets.controller;

import com.gzj.healthydiets.entity.Cart;
import com.gzj.healthydiets.entity.CartItem;
import com.gzj.healthydiets.entity.Food;
import com.gzj.healthydiets.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private FoodService foodService;

    /**
     * 功能1：加入购物车
     */
    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> add(Integer foodId, HttpSession session) {
        Food food = foodService.queryFoodById(foodId);
        CartItem cartItem = new CartItem(foodId, food.getName(), 1, food.getPrice(), food.getPrice());
        //2-创建或获取购物车
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        //3-将商品添加到购物车
        cart.addCartItem(cartItem);
        session.setAttribute("lastName",cartItem.getName());
        //4、将页面展示的数据封装到map中
        Integer itemTotalCount = cart.getItemTotalCount();
        String lastName = cartItem.getName();
        Map<String,Object> data=new HashMap<>();
        data.put("lastName",lastName);
        data.put("itemTotalCount",itemTotalCount);
        return data;
    }

    /**
     * 功能2:删除商品
     */
    @RequestMapping("/delete/{foodId}")
    public String delete(@PathVariable("foodId") Integer foodId, HttpSession session){
        //2-创建或获取购物车
        Cart cart = (Cart) session.getAttribute("cart");
        //3-将商品从购物车删除
        cart.deleteCartItem(foodId);
        //4-请求重定向到当前浏览页面
        return "redirect:/cart/cart_list.html";
    }

    /**
     * 功能3:清空购物车
     * @return
     */
    @RequestMapping("/clear")
    public String clear(HttpServletRequest request) {
        //1-创建或获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //2-将购物车清空
        if (cart != null) {
            cart.clear();
        }
        //3-请求重定向到当前浏览页面
        return "redirect:/cart/cart_list.html";
    }

    /**
     * 功能4:修改商品数量
     * @return
     */
    @RequestMapping("/update/{foodId}/{count}")
    public String update(@PathVariable("foodId") Integer foodId,@PathVariable("count") Integer count, HttpServletRequest request) {
        //2-创建或获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //3-更改商品数量
        if (cart != null) {
            cart.updateCartItemCount(foodId, count);
        }
        //4-请求重定向到当前浏览页面
        return "redirect:/cart/cart_list.html";
    }
}
