package com.github.ji.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jishenglong on 2023/3/24 16:17
 * 微信支付通知
 */
@NoArgsConstructor
@Data
public class WxPayNotifyDTO {

    @ApiModelProperty(value = "小程序ID")
    private String appid;

    @ApiModelProperty(value = "商家数据包")
    private String attach;

    @ApiModelProperty(value = "付款银行")
    private String bank_type;

    @ApiModelProperty(value = "货币种类")
    private String fee_type;

    @ApiModelProperty(value = "是否关注公众账号 用户是否关注公众账号，Y-关注，N-未关注")
    private String is_subscribe;

    @ApiModelProperty(value = "商户号")
    private String mch_id;

    @ApiModelProperty(value = "随机字符串")
    private String nonce_str;

    @ApiModelProperty(value = "用户在商户appid下的唯一标识")
    private String openid;

    @ApiModelProperty(value = "商户订单号")
    private String out_trade_no;

    @ApiModelProperty(value = "业务结果")
    private String result_code;

    @ApiModelProperty(value = "返回状态码")
    private String return_code;

    @ApiModelProperty(value = "签名")
    private String sign;

    @ApiModelProperty(value = "支付完成时间")
    private String time_end;

    @ApiModelProperty(value = "订单金额")
    private Integer total_fee;

    @ApiModelProperty(value = "总代金券金额")
    private String coupon_fee;

    @ApiModelProperty(value = "代金券使用数量")
    private String coupon_count;

    @ApiModelProperty(value = "代金券类型")
    private String coupon_type;

    @ApiModelProperty(value = "代金券ID")
    private String coupon_id;

    @ApiModelProperty(value = "交易类型 JSAPI、NATIVE、APP")
    private String trade_type;

    @ApiModelProperty(value = "微信支付订单号")
    private String transaction_id;
}
