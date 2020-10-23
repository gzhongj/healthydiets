package com.gzj.healthydiets.config;

import com.gzj.healthydiets.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //登录页面
        registry.addViewController("/").setViewName("user/login");
        registry.addViewController("/user/register.html").setViewName("user/register");
        registry.addViewController("/index.html").setViewName("food/index");
        registry.addViewController("/food/rank.html").setViewName("food/rank");
        registry.addViewController("/food/food_manager.html").setViewName("food/food_manager");
        registry.addViewController("/order/order_manager.html").setViewName("order/order_manager");
        registry.addViewController("/order/myorder_manager.html").setViewName("order/myorder_manager");
        registry.addViewController("/cart/cart_list.html").setViewName("cart/cart_list");
        registry.addViewController("/order/checkout.html").setViewName("order/checkout");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] addPathPatterns={"/*"};
        String[] excludePathPatterns={"/","/user/login","/user/register","/user/existUsername"};
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}
