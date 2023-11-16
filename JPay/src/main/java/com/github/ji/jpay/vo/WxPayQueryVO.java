package com.github.ji.jpay.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jishenglong on 2023/3/27 15:04
 */
@NoArgsConstructor
@Data
public class WxPayQueryVO {

    @ApiModelProperty(value = "是否关注公众账号")
    private String is_subscribe;

    @ApiModelProperty(value = "交易类型  JSAPI -JSAPI支付,NATIVE -Native支付,APP -APP支付")
    private String trade_type;

    @ApiModelProperty(value = "银行类型，采用字符串类型的银行标识")
    private String bank_type;

    @ApiModelProperty(value = "订单总金额，单位为分")
    private Integer total_fee;


    @ApiModelProperty(value = "标价币种")
    private String fee_type;

    @ApiModelProperty(value = "微信支付订单号")
    private String transaction_id;


    @ApiModelProperty(value = "商户订单号")
    private String out_trade_no;

    @ApiModelProperty(value = "附加数据")
    private String attach;

    @ApiModelProperty(value = "支付完成时间")
    private String time_end;

    @ApiModelProperty(value = "交易状态  SUCCESS--支付成功,REFUND--转入退款,NOTPAY--未支付,CLOSED--已关闭,REVOKED--已撤销(刷卡支付),USERPAYING--用户支付中,PAYERROR--支付失败(其他原因，如银行返回失败),ACCEPT--已接收，等待扣款")
    private String trade_state;
}
