package com.hit.store.mapper;


import com.hit.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert() {
        User user = new User();
        user.setUsername("second");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);

    }
    @Test
    public void updatePassword() {
        Integer uid  = 17;
        String modifiedUser = "smy";
        Date modifiedTime = new Date();
        String password = "12345678";
        userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
        System.out.println("success!");

    }
    @Test
    public void updateInfo() {
        User user = new User();
        user.setUid(25);
        user.setPhone("18245622155");
        user.setEmail("1736198425@qq.com");
        user.setGender(1);
        userMapper.updateInfo(user);
        System.out.println("success!");
    }
    @Test
    public void updateAvatar() {
        User user = new User();
        user.setUid(25);
        user.setAvatar("/update/1.png");
        user.setModifiedUser("宋明烨");
        user.setModifiedTime(new Date());
        userMapper.updateAvatar(user);
        System.out.println("success!");
    }
}
