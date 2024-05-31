package com.hit.store.service;

import com.hit.store.entity.User;
import com.hit.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("滕博");
            user.setPassword("12345678");
            userService.reg(user);
            System.out.println("success!");
        } catch (ServiceException e) {
            //获取异常信息与异常类名
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login() {
        String username = "滕";
        String password = "12345678";
        try {
            userService.login(username, password);
            System.out.println("success");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void updatePassword() {
        Integer uid = 25;
        String oldPassword = "12345678";
        String newPassword = "1234567";
        try {
            userService.updatePassword(uid, oldPassword, newPassword);
            System.out.println("success");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void getUserByUid() {
        Integer uid = 25;
        System.out.println(userService.getUserByUid(uid));
    }
    @Test
    public void updateInfo() {
        User user = new User();
        user.setUid(25);
        user.setPhone("18245666666");
        user.setEmail("@163.com");
        user.setGender(0);
        try {
            userService.updateInfo(user);
            System.out.println("success");
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void updateAvatar() {
        User user = new User();
        user.setUid(25);
        user.setUsername("滕老二");
        user.setAvatar("/update/avatar.png");
        try {
            userService.updateAvatar(user);
            System.out.println("success!");
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

}
