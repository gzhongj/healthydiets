package com.gzj.healthydiets.controller;

import com.gzj.healthydiets.entity.Cart;
import com.gzj.healthydiets.entity.Order;
import com.gzj.healthydiets.entity.OrderItem;
import com.gzj.healthydiets.entity.User;
import com.gzj.healthydiets.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController{
    @Autowired
    private OrderService orderService;
    /**
     * 功能1：创建新订单
     */
    @RequestMapping("/create")
    public String create(HttpSession session) {
        //1-获取session中的cart对象，获取session中的user对象的id属性
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        //2-调用orderService中的create()
        String orderId = orderService.create(cart, userId);
        Order order = orderService.showOneOrder(orderId);
        // 3-获取订单信息
        List<OrderItem> orderItems = orderService.showOrderDetails(orderId);
        session.removeAttribute("cart");
        session.setAttribute("orderItems",orderItems);
        session.setAttribute("order", order);
        //3-请求重定向到结算页面 /pages/cart/checkout.jsp
       return "redirect:/order/checkout.html";
    }
    /**
     * 功能2：展示个人订单
     * @return
     */
    @RequestMapping("/showOrders")
    public String showOrders(HttpSession session) {
        User user = (User) session.getAttribute("user");
        //1调用orderService中的showOrder()
        List<Order> orders = orderService.showOrder(user.getId());
        //2-将orders保存在request里
        session.setAttribute("orders", orders);
        //3-请求转发到我的订单页面 "pages/order/order_manager.jsp"
        return "redirect:/order/myorder_manager.html";
    }

    /**
     * 功能3：展示所有订单
     * @return
     */
    @RequestMapping("/showAllOrders")
    public String showAllOrders(HttpSession session) {
        //1调用orderService中的showAllOrder()
        List<Order> orders = orderService.showAllOrders();
        //2-将orders保存在request里
        session.setAttribute("orders", orders);
        //3-请求转发到订单管理页面 "/pages/manager/order_manager.jsp"
        return "redirect:/order/order_manager.html";
    }

    /**
     * 功能4：展示订单详情
     * @return
     */
    @RequestMapping("/orderDetails/{orderId}")
    public String orderDetails(@PathVariable("orderId") String orderId, Model model) {
        //2-根据订单编号获取订单里的商品信息
        List<OrderItem> orderItems = orderService.showOrderDetails(orderId);
        Order order = orderService.showOneOrder(orderId);
        //3-将商品信息保存在request中
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("price", order.getPrice());
        model.addAttribute("status", order.getStatus());
        //4-请求转发到订单详情页面
        return "order/order_details";
    }
    /**
     * 功能4-1：展示个人订单详情
     * @return
     */
    @RequestMapping("/myorderDetails/{orderId}")
    public String myorderDetails(@PathVariable("orderId") String orderId, Model model) {
        //2-根据订单编号获取订单里的商品信息
        List<OrderItem> orderItems = orderService.showOrderDetails(orderId);
        Order order = orderService.showOneOrder(orderId);
        //3-将商品信息保存在request中
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("price", order.getPrice());
        model.addAttribute("status", order.getStatus());
        //4-请求转发到订单详情页面
        return "order/myorder_details";
    }

    /**
     * 功能5：更改订单状态
     * @return
     */
    @RequestMapping("/updateOrderStatus/{status}/{orderId}")
    public String updateOrderStatus(HttpSession session,@PathVariable("orderId") String orderId, @PathVariable("status") Integer status) {
        User user = (User)session.getAttribute("user");
        //2-根据订单编号更改订单状态
        status=status+1;
        int i = orderService.updateOrderStatus(orderId, status);
        if (i == -1) {
            throw new IllegalArgumentException("订单状态异常");
        } else {
            if (user.getAuthority()==1){
                return "forward:/order/showAllOrders";
            }else{
                return "forward:/order/showOrders";
            }
        }
    }

    /**
     * 功能6：取消订单
     * @return
     */
    @RequestMapping("/cancel")
    public String cancel(String orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("订单状态异常，未获取订单编号");
        } else {
            //2-根据orderId更改order的status，以及orderItem中的相关数据
            orderService.updateOrderStatus(orderId,4);
        }
        return "myorderdetails";
    }
    /**
     * 功能7：删除订单
     * @return
     */
    @RequestMapping("/delete")
    public String delete(String orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("订单状态异常，未获取订单编号");
        } else {
            //2-根据orderId更改order的status，以及orderItem中的相关数据
            if(orderService.delete(orderId)){
                return "myorderdetails";
            }else{
                throw new IllegalArgumentException("删除订单失败！");
            }
        }
    }
}
