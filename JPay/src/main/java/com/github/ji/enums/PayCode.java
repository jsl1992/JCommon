package com.github.ji.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付编码 规则前面两位编码是支付厂商，后面两位编码是支付类型。
 */
@Getter
@AllArgsConstructor
public enum PayCode {

    WX_JSAPI(1001, "微信小程序"),
    WX_H5(1002, "微信H5"),
    WX_APP(1003, "微信APP"),
    SZRMB(1101, "数字人民币");


    private Integer code;
    private String name;


}
