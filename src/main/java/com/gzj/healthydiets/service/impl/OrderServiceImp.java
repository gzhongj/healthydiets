package com.gzj.healthydiets.service.impl;

import com.gzj.healthydiets.dao.FoodDao;
import com.gzj.healthydiets.dao.OrderDao;
import com.gzj.healthydiets.dao.OrderItemDao;
import com.gzj.healthydiets.dao.RankDaoImpl;
import com.gzj.healthydiets.entity.*;
import com.gzj.healthydiets.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private FoodDao foodDao;
    @Autowired
    private RankDaoImpl rankDao;
    @Override
    /**
     * 创建订单
     */
    @Transient
    public String create(Cart cart, Integer userId){
        if (cart ==null || userId == null){
            throw new IllegalArgumentException("cart or userId is null or empty!");
        }
        //创建订单编号
        String orderId = createOrderId(userId);
        //1-创建order对象，调用orderDao的add()
        Order order = new Order(null,orderId,userId, new Date(),cart.getItemTotalPrice(),0);
        orderDao.add(order);
        //2-创建orderItem对象,调用orderItemDao的add()
        //3-创建food，调用foodDao的update()，更新销量和库存
        for (Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()){
            OrderItem orderItem = new OrderItem(null,entry.getValue().getId(), orderId, entry.getValue().getName(),
                    entry.getValue().getCount(),entry.getValue().getSinglePrice(), entry.getValue().getTotalPrice());
            orderItemDao.add(orderItem);
            //            更新持久层的销量
            Food food=foodDao.queryFoodById(entry.getValue().getId());
            food.setSales(food.getSales()+entry.getValue().getCount());
            foodDao.updateFoodSales(food);
            //         更新redis中saleRank的销量（score）
            rankDao.setSale(food.getId(),entry.getValue().getCount());
        }
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {

        return orderDao.showAllOrder();
    }

    @Override
    public List<Order> showOrder(Integer userId) {

        return orderDao.showOrder(userId);
    }

    @Override
    public List<OrderItem> showOrderDetails(String orderId) {
        return orderItemDao.showOrderDetails(orderId);
    }

    @Override
    public int updateOrderStatus(String orderId, int status) {
        return orderDao.updateOrderStatus(orderId,status);
    }

    @Override
    public boolean delete(String orderId) {
        if (orderDao.delete(orderId)>0&&orderItemDao.delete(orderId)>0) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 通过订单ID获取订单信息
     * @param orderId
     * @return
     */
    @Override
    public Order showOneOrder(String orderId) {
        return orderDao.showOneOrder(orderId);
    }

    /**
     * 生成订单编号
     * @param userId
     * @return
     */
    private String createOrderId(Integer userId){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
         return sdf.format(date) + userId;
    }
}
