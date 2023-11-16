package com.github.ji.service;


import com.github.ji.dto.PayDTO;
import com.github.ji.dto.RefundDTO;
import com.github.ji.enums.PayCode;
import com.github.ji.vo.PayVO;

/**
 * 支付 PayService(统一的支付Service方便将来扩展)
 *
 * @author jishenglong on 2023/3/27 10:02
 **/
public interface PayService {


    PayCode getPayCode();

    /**
     * 支付
     *
     * @param payDTO payDTO
     * @return com.github.ji.vo.PayVO
     * @author jisl on 2023/11/16 11:18
     **/
    PayVO pay(PayDTO payDTO);


    /**
     * 交易是否成功
     *
     * @param merchantId 门店id
     * @param outTradeNo 交易订单号
     * @param payAmount  支付金额
     */
    Boolean isPaySuccess(Integer merchantId, String outTradeNo, Integer payAmount);

    /**
     * 退款
     *
     * @param refundDTO RefundDTO
     * @author jishenglong on 2023/4/19 15:23
     **/
    void refund(RefundDTO refundDTO);

    /**
     * 退款是否成功
     *
     * @param outRefundNo  退款单号
     * @param refundAmount 退款金额
     */
    Boolean isRefundSuccess(Integer merchantId, String outRefundNo, Integer refundAmount);

    /**
     * 关闭订单
     *
     * @param outTradeNo 交易订单号
     */
    void payClose(Integer merchantId, String outTradeNo);
}
