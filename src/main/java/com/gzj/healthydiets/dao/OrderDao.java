package com.gzj.healthydiets.dao;
import com.gzj.healthydiets.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
@Mapper
@Repository
public interface OrderDao {
    @Insert("insert into `t_order` (`order_id`,`user_id`,`create_time`,`price`,`status`) " +
            "values (#{order.orderId},#{order.userId},#{order.createTime},#{order.price},#{order.status})")
    int add(@Param(value ="order") Order order);

    @Select("select `id`,`order_id`,`user_id`,`create_time`,`price`,`status` from `t_order`")
    List<Order> showAllOrder();

    @Select("select `id`, `order_id`,`user_id`,`create_time`,`price`,`status` from `t_order`" +
            "where `user_id`=#{userId}")
    List<Order> showOrder(Integer userId);

    @Update("update t_order set `status`=#{status} where `order_id`=#{orderId}")
    int updateOrderStatus(String orderId, int status);

    @Delete("delete from t_order where `order_id`=#{orderId}")
    int delete(String orderId);

    @Select("select `id`,`order_id`,`user_id`,`create_time`,`price`,`status` from `t_order` " +
            "where `order_id`=#{orderId}")
    Order showOneOrder(String orderId);
}
