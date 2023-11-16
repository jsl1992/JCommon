package com.github.ji.jpay.dto;

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
public class WxPrepayDTO {


    @ApiModelProperty(value = "商品描述")
    private String body;

    @ApiModelProperty(value = "商户订单号")
    private String out_trade_no;


    @ApiModelProperty(value = "终端IP")
    private String spbill_create_ip;

    @ApiModelProperty(value = "openid")
    private String openid;

    @ApiModelProperty(value = "订单总金额，单位为分")
    private Integer total_fee;


    @ApiModelProperty(value = "通知地址")
    private String notify_url;

    @ApiModelProperty(value = "交易类型  JSAPI -JSAPI支付,NATIVE -Native支付,APP -APP支付")
    private String trade_type;


}
