package com.gzj.healthydiets.dao;

import com.gzj.healthydiets.entity.OrderItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface OrderItemDao {
    @Insert("insert into t_order_item (`item_id`,`order_id`,`name`,`count`,`single_price`,`total_price`) " +
            "values (#{orderItem.itemId},#{orderItem.orderId},#{orderItem.name},#{orderItem.count}," +
            "#{orderItem.singlePrice},#{orderItem.totalPrice})")
    int add(@Param(value="orderItem") OrderItem orderItem);
    @Select("select `id`,`item_id`,`order_id`,`name`,`count`,`single_price`,`total_price`" +
            "from t_order_item where order_id=#{orderId}")
    List<OrderItem> showOrderDetails(String orderId);
    @Delete("delete from t_order_item where `order_id`=#{orderId}")
    int delete(String orderId);
}
