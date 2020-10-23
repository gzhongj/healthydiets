package com.gzj.healthydiets.service;

import com.gzj.healthydiets.entity.User;

public interface UserService {
    /**
     * 1-判断用户名是否存在
     * @param username
     * @return 返回true说明用户名已存在，返回false说明用户名不存在，用户名可用
     */
    boolean existUsername(String username);

    /**
     * 2-用户注册
     * @param user
     */
    void registerUser(User user);
    /**
     *3-用户登录
     * @param user
     * @return
     */
    User loginUser(User user);

    /**
     * 4-根据用户名查询用户信息
     * @param username
     * @return
     */
    User queryUserByUsername(String username);
}
