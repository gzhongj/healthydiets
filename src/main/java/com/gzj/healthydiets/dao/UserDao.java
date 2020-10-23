package com.gzj.healthydiets.dao;
import com.gzj.healthydiets.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface UserDao {
    /**
     * 1根据用户名查询用户信息
     * @param username
     * @return 如果返回为null，则该用户不存在，反之用户存在
     */
    @Select("select id,username,password,email,authority from t_user where username=#{username}")
    public User queryUserByUsername(String username);

    /**
     * 2根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null，说明用户名或者密码有错误。
     */
    @Select("select id,username,password,email,authority from t_user where username=#{username} and password=#{password}")
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 3保存用户信息
     * @param user
     * @return
     */
    @Insert("insert into t_user (username,password,email,authority) values (#{user.username},#{user.password},#{user.email},#{user.authority})")
    public int saveUser(@Param("user") User user);
}
