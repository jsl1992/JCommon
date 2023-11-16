package com.github.ji.service.impl;


import com.github.ji.config.MerchantMatchWeChatConfig;
import com.github.ji.config.WxPayProperties;
import com.github.ji.dto.PayDTO;
import com.github.ji.enums.PayCode;
import com.github.ji.service.IMerchantMatchService;
import com.github.ji.vo.MerchantMatchVO;
import com.github.ji.vo.PayVO;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.util.NonceUtil;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.jsapi.model.*;
import com.wechat.pay.java.service.payments.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.Objects;

/**
 * 微信支付服务实现
 *
 * @author jishenglong on 2023/3/27 10:02
 **/
@Component
@Slf4j
public class WeChatJsapiPayServiceImpl extends WeChatPayService {

    @Resource
    private MerchantMatchWeChatConfig matchWeChatConfig;

    @Resource
    private IMerchantMatchService merchantMatchService;

    @Resource
    private WxPayProperties wxPayProperties;


    @Override
    public PayCode getPayCode() {
        return PayCode.WX_JSAPI;
    }

    @Override
    public PayVO pay(PayDTO payDTO) {
        final MerchantMatchVO merchantMatchVO = merchantMatchService.selectByMerchantId(payDTO.getMerchantId());
        final RSAAutoCertificateConfig certificateConfig = matchWeChatConfig.getCertificateConfig(merchantMatchVO);
        PrepayRequest request = new PrepayRequest();
        Amount amount = new Amount();
        amount.setTotal(payDTO.getAmount());
        request.setAmount(amount);
        request.setAppid(wxPayProperties.getAppid());
        request.setMchid(merchantMatchVO.getMchId());
        request.setDescription(payDTO.getDescription());
        request.setNotifyUrl(wxPayProperties.getDomainUrl() + "/payNotify/weChatPay/notifyOrder/" + payDTO.getMerchantId());
        request.setOutTradeNo(payDTO.getOutTradeNo());
        Payer payer = new Payer();
        payer.setOpenid(payDTO.getOpenid());
        request.setPayer(payer);


        String prepayId = getJsapiService(payDTO.getMerchantId()).prepay(request).getPrepayId();
        long timestamp = Instant.now().getEpochSecond();
        String nonceStr = NonceUtil.createNonce(32);
        String packageVal = "prepay_id=" + prepayId;
        String message = wxPayProperties.getAppid() + "\n" + timestamp + "\n" + nonceStr + "\n" + packageVal + "\n";
        log.debug("Message for RequestPayment signatures is[{}]", message);
        String sign = certificateConfig.createSigner().sign(message).getSign();
        PayVO response = new PayVO();
        response.setAppid(wxPayProperties.getAppid());
        response.setTimestamp(String.valueOf(timestamp));
        response.setNonceStr(nonceStr);
        response.setPackageVal(packageVal);
        response.setSignType(certificateConfig.getSignType());
        response.setPaySign(sign);
        return response;
    }

    private JsapiService getJsapiService(Integer merchantId) {
        return new JsapiService.Builder().config(matchWeChatConfig.getCertificateConfig(merchantId)).build();
    }


    @Override
    public Boolean isPaySuccess(Integer merchantId, String outTradeNo, Integer payAmount) {
        final QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();
        request.setMchid(merchantMatchService.selectByMerchantId(merchantId).getMchId());
        request.setOutTradeNo(outTradeNo);
        final Transaction transaction = getJsapiService(merchantId).queryOrderByOutTradeNo(request);
        return Objects.equals(transaction.getTradeState(), Transaction.TradeStateEnum.SUCCESS) && Objects.equals(transaction.getAmount().getTotal(), payAmount);
    }


    @Override
    public void payClose(Integer merchantId, String outTradeNo) {
        final CloseOrderRequest request = new CloseOrderRequest();
        request.setMchid(merchantMatchService.selectByMerchantId(merchantId).getMchId());
        request.setOutTradeNo(outTradeNo);
        getJsapiService(merchantId).closeOrder(request);
    }
}
