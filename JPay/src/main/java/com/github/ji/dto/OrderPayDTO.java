package com.github.ji.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 订单 支付DTO
 *
 * @author jisl on 2023/11/16 11:17
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "订单 支付入参")
public class OrderPayDTO {

    @ApiModelProperty(value = "订单编号")
    @NotNull
    private String orderSn;

    @ApiModelProperty(value = "openid (1001 微信小程序 不能为空)")
    private String openid;


    @ApiModelProperty(value = "支付编码 1001 微信小程序，1002 微信h5,1003 微信APP", required = true)
    @NotNull
    private Integer payCode;


}
