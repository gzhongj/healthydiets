package com.gzj.healthydiets.service.impl;


import com.gzj.healthydiets.dao.UserDao;
import com.gzj.healthydiets.entity.User;
import com.gzj.healthydiets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public boolean existUsername(String username) {
        if (userDao.queryUserByUsername(username)==null){
            return false;
        }else{
            return true;
        }
    }
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User loginUser(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public User queryUserByUsername(String username) {
        return userDao.queryUserByUsername(username);
    }
}
