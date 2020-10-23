package com.gzj.healthydiets.service;

import com.gzj.healthydiets.entity.Food;
import com.gzj.healthydiets.entity.Page;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public interface FoodService {
    public int addFood(Food food);
    public int deleteFoodById(Integer id);
    public int updatetFood(Food food);
    public Food queryFoodById(Integer id);
    public List<Food> queryFoods();
    public ArrayList<Food> queryFoodByIds(LinkedHashSet<String> ids);
    Page<Food> page(int pageNo, int pageSize);
    List<Food> setImgPath(List<Food> page);
}
