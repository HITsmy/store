package com.hit.store.controller;

import com.hit.store.controller.ex.*;
import com.hit.store.service.ex.*;
import com.hit.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller基类 用于处理异常情况
 */
public class BaseController {
    public static final int OK = 200;

    /**
     *
     * @param e 处理业务层异常
     * @return 由于RestController返回结果会被自动转为json格式给前端
     */
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handler(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UserDuplicateException) {
            result.setState(4000);
            result.setMessage("用户已被占用");
        } else if(e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("服务器出现未知错误");
        } else if(e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage("未检测到用户信息");
        } else if(e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("密码不正确");
        } else if (e instanceof UpdateException) {
            result.setState(5003);
            result.setMessage("更新时出现未知错误");
        } else if(e instanceof FileNullException) {
            result.setState(6000);
            result.setMessage("上传文件为空");
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("文件过大");

        } else if (e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("文件类型不正确");
        } else if(e instanceof FileIOException) {
            result.setState(6003);
            result.setMessage("文件IO时出现异常");
        } else if(e instanceof AddressCountException) {
            result.setState(7000);
            result.setMessage("收货地址不得超过20个");
        } else if (e instanceof ProductNotFoundException) {
            result.setState(8000);
            result.setMessage("商品信息未找到");
        } else if(e instanceof CartNotFoundException) {
            result.setState(9000);
            result.setMessage("购物车信息未找到");
        } else if(e instanceof OrderNotFoundException) {
            result.setState(10000);
            result.setMessage("订单信息未找到");
        }
        return result;
    }

    /**
     *
     * @param httpSession 全局session
     * @return 用户的uid
     */
    protected Integer getUidFromSession(HttpSession httpSession) {
        Integer uid = Integer.valueOf((httpSession.getAttribute("uid").toString()));
        return uid;
    }

    /**
     *
     * @param httpSession 全局session
     * @return  用户名
     */
    protected String getUsernameFromSession(HttpSession httpSession) {
        String username =  httpSession.getAttribute("username").toString();
        return username;
    }
}
