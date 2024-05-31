package com.hit.store.controller;

import com.hit.store.controller.ex.FileIOException;
import com.hit.store.controller.ex.FileNullException;
import com.hit.store.controller.ex.FileSizeException;
import com.hit.store.controller.ex.FileTypeException;
import com.hit.store.entity.User;
import com.hit.store.service.IUserService;
import com.hit.store.service.ex.InsertException;
import com.hit.store.service.ex.ServiceException;
import com.hit.store.service.ex.UserDuplicateException;
import com.hit.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    IUserService userService;


    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        JsonResult<Void> result = new JsonResult<>();
        userService.reg(user);
        result.setState(OK);
        return result;
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession httpSession) {
        JsonResult<User> result = new JsonResult<>();
        User user = userService.login(username, password);

        // 将uid和username保存到全局session中
        httpSession.setAttribute("uid", user.getUid());
        httpSession.setAttribute("username", user.getUsername());
        System.out.println(getUidFromSession(httpSession));
        System.out.println(getUsernameFromSession(httpSession));
        result.setState(OK);
        result.setData(user);
        return result;
    }
    @RequestMapping("updatePassword")
    public JsonResult<Void> updatePassword(String oldPassword,
                                           String newPassword, HttpSession session) {
        JsonResult<Void> result = new JsonResult<>();
        Integer uid = getUidFromSession(session);
        userService.updatePassword(uid, oldPassword, newPassword);
        result.setState(OK);
        return result;
    }
    @RequestMapping("getInfo")
    public JsonResult<User> getInfo(HttpSession session) {
        JsonResult<User> result = new JsonResult<>();
        Integer uid = getUidFromSession(session);
        User user = userService.getUserByUid(uid);
        result.setState(OK);
        result.setData(user);
        return result;
    }
    @RequestMapping("updateInfo")
    public JsonResult<Void> updateInfo(User user, HttpSession session) {
        JsonResult<Void> result = new JsonResult<>();
        Integer uid = getUidFromSession(session);
        user.setUid(uid);
        userService.updateInfo(user);
        result.setState(OK);
        return result;
    }

    private static final List<String> typeList = new ArrayList<>(); // 接受文件类型列表

    private static final int maxSize = 10 * 1024 * 1024; // 上传文件大小限制(10MB)
    static {
        typeList.add("image/jpeg");
        typeList.add("image/png");
        typeList.add("image/bmp");
        typeList.add("image/gif");
    }
    @RequestMapping("updateAvatar")
    public JsonResult<String> updateAvatar(@RequestParam("file") MultipartFile file, HttpSession session) {
        JsonResult<String> result = new JsonResult<>();
        // 检查文件格式
        if (file.isEmpty()) {
            throw new FileNullException("上传文件为空");
        }
        if (file.getSize() > maxSize) {
            throw new FileSizeException("上传文件过大");
        }
        if(!typeList.contains(file.getContentType())) {
            throw new FileTypeException("文件类型不正确");
        }

        // 获取当前服务器目录上下文
        String parent = session.getServletContext().getRealPath("upload");
        System.out.println(parent);
        //指向*/upload/目录
        File dir = new File(parent);
        if(!dir.exists()) {
            dir.mkdir();
        }
        //获取到文件原始名 比如 avatar.png
        String originalName = file.getOriginalFilename();
        System.out.println(originalName);
        //获取文件后缀 如.png
        int index = originalName.lastIndexOf(".");
        String suffix = originalName.substring(index);
        // 生成一个随机问价名
        String fileName = UUID.randomUUID().toString().toUpperCase() + suffix;
        File dest = new File(parent, fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileIOException("文件IO时候出现异常");
        }
        //用于传输数据
        User user = new User();
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        String avatar = "/upload/" + fileName;
        user.setUid(uid);
        user.setUsername(username);
        user.setAvatar(avatar);
        userService.updateAvatar(user);
        result.setState(OK);
        result.setData(avatar);
        return result;
    }
}
