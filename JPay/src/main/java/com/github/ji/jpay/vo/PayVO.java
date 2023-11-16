package com.github.ji.jpay.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 支付返回统一VO
 *
 * @author jishenglong on 2023/4/26 15:56
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayVO {


    @ApiModelProperty(value = "appid")
    private String appid;

    @ApiModelProperty(value = "商户号:商户号mchid对应的值")
    private String partnerId;

    @ApiModelProperty(value = "预支付交易会话ID")
    private String prepayId;

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
