package com.hit.store.controller;

import com.hit.store.entity.OrderStr;
import com.hit.store.service.IPayService;
import com.hit.store.util.JsonResult;
import com.hit.store.util.RSAEncrypt;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.DigestUtils;
@RestController
@RequestMapping("pay")
public class PayController extends BaseController{
    static Integer orderNumber = 1;
    @Autowired
    IPayService payService;

    @RequestMapping("/")
    public JsonResult<String> payAOrder(Integer oid, Integer initiatorID, HttpSession session) throws Exception {
        JsonResult<String> result = new JsonResult<>();
        Long totalPrice = payService.PayAOderByOid(oid, getUsernameFromSession(session));
        OrderStr orderStr = new OrderStr();
        orderStr.setInitiatorID(initiatorID);
        orderStr.setInitiatorName(getUsernameFromSession(session));
        orderStr.setReceiverID(1);
        orderStr.setReceiverName("SMYMall");
        orderStr.setAmount(totalPrice);
        orderStr.setOrderNumber(orderNumber);
        System.out.println(totalPrice);
        String privateKey = "";
        String publicKey = "";
//        String hashCode = DigestUtils.md5DigestAsHex(orderStr.toString().getBytes());
//        String S = RSAEncrypt.privateKeyEncrypt(hashCode, privateKey, "xxx");
        String S = "";
        String certificate = "";

        // 返回给前端的数据——一个支付链接
        String data = String.format("http://localhost:9528/#/pay?initiatorName=\"%s\"&initiatorID=%d&receiverName=\"%s\"&receiverID=%d&amount=%d&S=\"%s\"&certificate=\"%s\"&publickey=\"%s\"&orderNumber=%d",
                orderStr.getInitiatorName(), orderStr.getInitiatorID(), orderStr.getReceiverName(), orderStr.getReceiverID(), orderStr.getAmount(), S, certificate, publicKey, orderNumber);
        orderNumber ++;
        result.setData(data);
        return result;
    }
}
