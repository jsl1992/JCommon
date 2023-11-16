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
public class PayDTO {

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

    @ApiModelProperty(value = "支付编码")
    private Integer payCode;

}
