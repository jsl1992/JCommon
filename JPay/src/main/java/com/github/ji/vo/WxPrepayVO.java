package com.github.ji.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WxPrepayVO {


    @ApiModelProperty(value = "appId")
    private String appId;

    @ApiModelProperty(value = "时间戳")
    private String timestamp;

    @ApiModelProperty(value = "随机数")
    private String nonceStr;

    @ApiModelProperty(value = "订单详情扩展字符串")
    private String packageVal;

    @ApiModelProperty(value = "签名方式")
    private String signType;

    @ApiModelProperty(value = "签名")
    private String paySign;


}
