package com.hit.store.service;

import com.hit.store.entity.User;

import java.util.Date;

/**
 *  用户模块业务层接口
 */
public interface IUserService {
    /**
     * 用户注册方法
     * @param user
     */
    void reg(User user);
    User login(String username, String password);
    void updatePassword(Integer uid, String oldPassword, String newPassword);
    User getUserByUid(Integer uid);
    void updateInfo(User user);
    void updateAvatar(User user);
}
