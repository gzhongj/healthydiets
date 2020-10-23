package com.gzj.healthydiets.service;


import com.gzj.healthydiets.entity.Cart;
import com.gzj.healthydiets.entity.Order;
import com.gzj.healthydiets.entity.OrderItem;

import java.util.List;

public interface OrderService {
     String create(Cart cart, Integer userId);

     List<Order> showAllOrders();

    List<Order> showOrder(Integer userId);

    List<OrderItem> showOrderDetails(String orderId);

    int updateOrderStatus(String orderId, int status);

    boolean delete(String orderId);

    Order showOneOrder(String orderId);
}
