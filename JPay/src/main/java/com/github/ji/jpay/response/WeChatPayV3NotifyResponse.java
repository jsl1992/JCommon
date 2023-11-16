package com.github.ji.jpay.response;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

/**
 * 微信支付通知返回
 *
 * @author jishenglong on 2023/3/27 14:48
 **/
public class WeChatPayV3NotifyResponse {


    public static ResponseEntity<String> returnInfo(String code, String message) {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("message", message);
        HttpStatus httpStatus = Objects.equals(code, "SUCCESS") ? HttpStatus.OK : HttpStatus.SERVICE_UNAVAILABLE;
        return new ResponseEntity<>(jsonObject.toJSONString(), httpStatus);
    }

    public static ResponseEntity<String> success() {
        return returnInfo("SUCCESS", "OK");
    }

    public static ResponseEntity<String> fail(String message) {
        return returnInfo("FAIL", message);
    }


}
