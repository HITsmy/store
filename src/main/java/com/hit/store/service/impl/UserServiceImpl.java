package com.hit.store.service.impl;

import com.hit.store.entity.User;
import com.hit.store.mapper.UserMapper;
import com.hit.store.service.IUserService;
import com.hit.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     *  业务层注册函数
     * @param user 用户信息
     */
    @Override
    public void reg(User user) {
        String username = user.getUsername();

        //通过mapper层的findByUsername方法，检查用户是否重复
        User result = userMapper.findByUsername(username);
        if(result != null) {
            // 抛出用户已被占用异常
            throw new UserDuplicateException("用户名已被占用");
        }

        //设置已删除字段为 0
        user.setIsDelete(0);

        //将密码通过与盐值合并后进行md5加密 得到密文
        String salt = UUID.randomUUID().toString().toUpperCase();
        String oldPassword = user.getPassword();
        user.setPassword(this.md5(oldPassword, salt));
        user.setSalt(salt);

        // 设置四个修改有关的日志
        Date date = new Date();
        user.setCreateUser(user.getUsername());
        user.setCreatedTime(date);
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(date);
        Integer rows = userMapper.insert(user);
        if(rows != 1) {
            // 抛出未知异常
            throw new InsertException("在用户注册时出现未知异常");
        }
    }

    /**
     *  业务层登录函数
     * @param username 用户名
     * @param password 密码
     * @return 返回用户部分信息便于给前端进行展示
     */
    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        // 检查用户信息存在时候要检查is_delete字段
        if(result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户信息不存在");
        }
        // 对从客户端获得的密码进行md5变化后与数据库密码对比
        String salt = result.getSalt();
        String oldPassword = result.getPassword();
        String newPassword = md5(password, salt);
        if(!oldPassword.equals(newPassword)) {
            throw new PasswordNotMatchException("密码不正确");
        }
        // 获取到用户数据便于前端进行展示
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }



    /**
     *  业务层修改密码函数
     * @param uid
     * @param oldPassword
     * @param newPassword
     */
    @Override
    public void updatePassword(Integer uid, String oldPassword, String newPassword) {
        // 首先判断该用户是否存在
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }

        // 校验旧密码是否正确
        String salt = result.getSalt();
        String rightPassword = result.getPassword();
        oldPassword = md5(oldPassword, salt);
        if(!oldPassword.equals(rightPassword)) {
            throw new PasswordNotMatchException("密码不正确");
        }
        newPassword = md5(newPassword, salt);
        String modifiedUser = result.getUsername();
        Date modifiedTime = new Date();
        Integer rows = userMapper.updatePassword(uid, newPassword, modifiedUser, modifiedTime);
        if(rows == 0) {
            throw new UpdateException("更新用户信息时出现未知错误");
        }
    }

    @Override
    public User getUserByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void updateInfo(User user) {
        Integer uid = user.getUid();
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfo(user);
        if(rows == 0) {
            throw new UpdateException("更新信息时出现未知错误");
        }
    }

    @Override
    public void updateAvatar(User user) {
        Integer uid = user.getUid();
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateAvatar(user);
        if(rows == 0) {
            throw new UpdateException("更新时出现未知错误");
        }
    }


    /**
     *
     * md5 加密算法 将明文密码加密为密文
     * @param oldPassword
     * @param salt
     * @return
     */
    private String md5(String oldPassword, String salt) {
        String newPassword = null;

        //进行三次md5变换得到新密码密文
        for(int i=0; i<3; i++) {
            newPassword = DigestUtils.md5DigestAsHex((salt + oldPassword + salt).getBytes()).toUpperCase();
        }
        return newPassword;
    }
}
