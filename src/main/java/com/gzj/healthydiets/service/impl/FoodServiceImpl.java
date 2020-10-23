package com.gzj.healthydiets.service.impl;

import com.gzj.healthydiets.dao.FoodDao;
import com.gzj.healthydiets.entity.Food;
import com.gzj.healthydiets.entity.Page;
import com.gzj.healthydiets.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodDao foodDao;
    @Override
    public Page<Food> page(int pageNo, int pageSize) {
        Page<Food> page=new Page<>();
        //2、pageSize
        page.setPageSize(pageSize);
        //3、pageTotalCount
        int pageTotalCount=foodDao.pageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //4、pageTotal
        int pageTotal=pageTotalCount/pageSize;
        if (pageTotalCount%pageSize!=0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);
        //1、pageNo
        page.setPageNo(pageNo);
        //5、itms
        int begin=(pageNo-1)*pageSize;
        List<Food> items=foodDao.queryItems(begin,pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public List<Food> setImgPath(List<Food> foods) {
        for (Food food:foods) {
            food.setImgPath("/food/img?imgPath="+food.getImgPath());
        }
        return foods;
    }

    @Override
    public int addFood(Food food) {
        return foodDao.addFood(food);
    }

    @Override
    public int deleteFoodById(Integer id) {
        return foodDao.deleteFoodById(id);
    }

    @Override
    public int updatetFood(Food food) {
        if("default.jpg".equals(food.getImgPath())){
            food.setImgPath(foodDao.queryFoodById(food.getId()).getImgPath());
        }
        return foodDao.updateFood(food);
    }

    @Override
    public Food queryFoodById(Integer id) {
        return foodDao.queryFoodById(id);
    }

    @Override
    public List<Food> queryFoods() {
        return foodDao.queryFoods();
    }

    @Override
    public ArrayList<Food> queryFoodByIds(LinkedHashSet<String> ids) {
        ArrayList<Food> foods = new ArrayList<>();
        Iterator<String> iterator = ids.iterator();
        while(iterator.hasNext()){
            Food food = foodDao.queryFoodById(Integer.parseInt(iterator.next()));
            foods.add(food);
        }
        return foods;
    }
}
