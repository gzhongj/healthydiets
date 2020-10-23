package com.gzj.healthydiets.controller;

import com.gzj.healthydiets.entity.Food;
import com.gzj.healthydiets.entity.Page;
import com.gzj.healthydiets.service.FileUploadService;
import com.gzj.healthydiets.service.FoodService;
import com.gzj.healthydiets.service.RankServic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Controller
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private RankServic rankServic;
    @Autowired
    private FileUploadService fileUploadService;
    /**
     * 1-分页显示食谱信息
     */
    @RequestMapping("/page/{pageNo}")
    public String page(HttpSession session, @PathVariable("pageNo") Integer pageNo) {
        Integer pageSize=5;
        Page<Food> page = foodService.page(pageNo, pageSize);
        List<Food> foods = foodService.setImgPath(page.getItems());
        page.setItems(foods);
        session.setAttribute("page", page);
        session.setAttribute("pageNo",page.getPageNo());
        session.setAttribute("pageTotal",page.getPageTotal());
        session.setAttribute("pageTotalCount",page.getPageTotalCount());
        return "redirect:/index.html";
    }
    /**
     * 1-1显示销量榜单
     */
    @RequestMapping("/rank")
    public String saleRank(HttpSession session) {
        LinkedHashSet<String> rank = rankServic.getRank();
        ArrayList<Food> foods = foodService.queryFoodByIds(rank);
        List<Food> newFoods = foodService.setImgPath(foods);
        if (session.getAttribute("foods")!=null){
            session.removeAttribute("foods");
        }
        session.setAttribute("foods",newFoods);
        return "redirect:/food/rank.html";
    }
    /**
     * 1-2显示图片
     * @return
     */
    @RequestMapping(value = "/img",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] img(@RequestParam("imgPath") String imgPath, HttpSession session) throws IOException {

        File file = new File("/Users/jessica/img/" + imgPath );
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }
    /**
     * 1-3显示食谱详情
     */
    @RequestMapping("/details")
    public String details() {
        return "food/food_details";
    }
    /**
     * 2-添加食谱
     * @return
     */
    @RequestMapping("/add")
    public String add(Food food, MultipartFile imgfile,HttpSession session) {
        //2、通过foodService将food对象添加到数据库
        String imgPath = fileUploadService.fileUpload(imgfile);
        if (!imgPath.contains("error")){
            food.setImgPath(imgPath);
        }else{
            food.setImgPath("default.jpg");
        }
        food.setSales(0);
        foodService.addFood(food);
        Integer pageNo= (Integer) session.getAttribute("pageNo");
        //3、请求转发到"/manager/foodServlet？action=list"
        return "forward:/food/manager/"+pageNo;
    }

    /**
     * 3-删除食谱信息
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, HttpSession session) {
        //2、通过foodService将对应id的food从数据库中删除
        foodService.deleteFoodById(id);
        Integer pageNo = (Integer) session.getAttribute("pageNo");
        //3、请求重定向到"/manager/foodServlet？action=list"
        return "forward:/food/manager/"+pageNo;
    }

    /**
     * 4-获取食谱信息并回显到页面
     * @return
     */
    @RequestMapping("/get/{foodId}")
    public String getFood(@PathVariable("foodId") Integer id, Model model) {
        if (id==0){
            return "food/food_add";
        }else {
            //2、通过foodService的queryfoodById获取food对象
            Food food = foodService.queryFoodById(id);
            //3、将food对象存储在request域中
            model.addAttribute("food", food);
            //4、请求转发到
            return "food/food_update";
        }
    }

    /**
     * 5-更新食谱信息
     * @return
     */
    @RequestMapping("/update/{foodId}")
    public String update(@PathVariable("foodId")Integer foodId,Food food,HttpSession session,MultipartFile imgfile) {
        //2、通过foodService的update方法更新数据库
        System.out.println(food);
        food.setId(foodId);
        System.out.println(food);
        String imgPath = fileUploadService.fileUpload(imgfile);
        if (!imgPath.contains("error")){
            food.setImgPath(imgPath);
        }else{
            food.setImgPath("default.jpg");
        }
        System.out.println(food);
        foodService.updatetFood(food);
        Integer pageNo = (Integer)session.getAttribute("pageNo");
        //3、请求重定向到"/manager/foodServlet？action=list"
        return "forward:/food/manager/"+pageNo;
    }

    /**
     * 6-食谱管理页显示所有食谱信息
     */
    @RequestMapping("/manager/{pageNo}")
    public String manager(@PathVariable("pageNo") Integer pageNo,HttpSession session) {
        Integer pageSize=5;
        Page<Food> page = foodService.page(pageNo, pageSize);
        List<Food> foods = page.getItems();
        Integer pageTotal = page.getPageTotal();
        session.setAttribute("foods", foods);
        session.setAttribute("pageNo",pageNo);
        session.setAttribute("pageTotal",pageTotal);
        return "redirect:/food/food_manager.html";
    }
}

