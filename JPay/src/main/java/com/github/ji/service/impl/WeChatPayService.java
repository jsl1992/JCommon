package com.github.ji.service.impl;


import com.github.ji.config.MerchantMatchWeChatConfig;
import com.github.ji.config.WxPayProperties;
import com.github.ji.dto.RefundDTO;
import com.github.ji.service.PayService;
import com.wechat.pay.java.service.refund.RefundService;
import com.wechat.pay.java.service.refund.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 微信支付抽象类（退款是相同的sdk放在这里）
 *
 * @author jishenglong on 2023/3/27 10:02
 **/
@Component
@Slf4j
public abstract class WeChatPayService implements PayService {

    @Resource
    private MerchantMatchWeChatConfig matchWeChatConfig;


    @Resource
    private WxPayProperties wxPayProperties;


    @Override
    public void refund(RefundDTO refundDTO) {
        final RefundService refundService = new RefundService.Builder().config(matchWeChatConfig.getCertificateConfig(refundDTO.getMerchantId())).build();
        CreateRequest request = new CreateRequest();
        request.setOutTradeNo(refundDTO.getOutTradeNo());
        request.setOutRefundNo(refundDTO.getOutRefundNo());
        request.setNotifyUrl(wxPayProperties.getDomainUrl() + "/payNotify/weChatPay/refundOrder/" + refundDTO.getMerchantId());
        final AmountReq amountReq = new AmountReq();
        amountReq.setRefund(Long.valueOf(refundDTO.getRefundAmount()));
        amountReq.setTotal(Long.valueOf(refundDTO.getAmount()));
        amountReq.setCurrency("CNY");
        request.setAmount(amountReq);
        refundService.create(request);
    }

    @Override
    public Boolean isRefundSuccess(Integer merchantId, String outRefundNo, Integer refundAmount) {
        final RefundService refundService = new RefundService.Builder().config(matchWeChatConfig.getCertificateConfig(merchantId)).build();
        QueryByOutRefundNoRequest request = new QueryByOutRefundNoRequest();
        request.setOutRefundNo(outRefundNo);
        final Refund refund = refundService.queryByOutRefundNo(request);
        return Objects.equals(refund.getStatus(), Status.SUCCESS) && Objects.equals(refund.getAmount().getRefund().intValue(), refundAmount);
    }


}
