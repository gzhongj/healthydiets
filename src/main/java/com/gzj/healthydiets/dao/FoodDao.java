package com.gzj.healthydiets.dao;

import com.gzj.healthydiets.entity.Food;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Repository
public interface FoodDao {
    /**
     *1、添加食谱
     * @param food
     * @return
     */
    @Insert("insert into t_food (`name`,`chef`,`sales`,`energy`,`price`,`img_path`)" +
            "values(#{food.name},#{food.chef},#{food.sales},#{food.energy},#{food.price},#{food.imgPath})")
    public int addFood(@Param("food") Food food);

    /**
     * 2、通过id删除食谱
     * @param id
     * @return
     */
    @Delete("delete from t_food where id = #{id}")
    public int deleteFoodById(Integer id);

    /**
     * 3、修改食谱信息
     * @param food
     * @return
     */
    @Update("update t_food set `name`=#{food.name},`chef`=#{food.chef},`energy`=#{food.energy},`price`=#{food.price},`img_path`=#{food.imgPath} where id=#{food.id}")
    public int updateFood(@Param("food") Food food);
    /**
     * 3-1、修改食谱销量
     * @param food
     * @return
     */
    @Update("update t_food set `sales`=#{food.sales} where id=#{food.id}")
    public int updateFoodSales(@Param("food") Food food);
    /**
     * 4、通过id查询食谱信息
     * @param id
     * @return
     */
    @Select("select `id`,`name`,`chef`,`sales`,`energy`,`price`,`img_path` from t_food where id=#{id}")
    public Food queryFoodById(Integer id);

    /**
     * 5、查询多条食谱信息
     * @return
     */
    @Select("select `id`,`name`,`chef`,`sales`,`energy`,`price`,`img_path` from t_food")
    public List<Food> queryFoods();
    /**
     * 6、获取表中总记录数
     * @return
     */
    @Select("select count(*) from t_food")
    public int pageTotalCount();

    /**
     * 7、获取分页中的食谱信息
     * @return
     */
    @Select("select `id`,`name`,`chef`,`sales`,`energy`,`price`,`img_path` from t_food limit #{begin},#{pageSize}")
    List<Food> queryItems(int begin, int pageSize);

    /**
     * 8-获取价格区间内的总条目数
     * @param minPrice
     * @param maxPrice
     * @return
     */
    @Select("select count(*) from t_food where price between #{minPrice} and #{maxPrice}")
    public int pageTotalCountByPrice(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * 9-获取价格区间内的多条食谱信息
     * @param minPrice
     * @param maxPrice
     * @param begin
     * @param pageSize
     * @return
     */
    @Select("select `id`,`name`,`chef`,`sales`,`energy`,`price`,`img_path` from t_food " +
            "where price between #{minPrice} and #{maxPrice} order by price limit #{begin},#{pageSize}")
    public List<Food> queryItemsByPrice(BigDecimal minPrice, BigDecimal maxPrice, int begin, int pageSize) ;
}
