package com.github.ji.jpay.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jishenglong on 2023/3/24 15:25
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Order {

    @ApiModelProperty(value = "门店id")
    private Integer merchantId;

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "商户订单号")
    private String outTradeNo;


    @ApiModelProperty(value = "openid")
    private String openid;

    @ApiModelProperty(value = "订单总金额，单位为分")
    private Integer amount;

    @ApiModelProperty(value = "支付编码 1001 微信小程序，1002 微信h5,1003 微信APP")
    private Integer payCode;

}
