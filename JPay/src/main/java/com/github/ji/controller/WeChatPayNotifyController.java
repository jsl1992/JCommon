package com.github.ji.controller;

import com.github.ji.config.MerchantMatchWeChatConfig;
import com.github.ji.response.WeChatPayV3NotifyResponse;
import com.github.ji.utils.HttpContextUtil;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.RefundNotification;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author jisl on 2023/11/16 10:38
 **/
@RestController
@Api(value = "支付通知", tags = {"微信支付通知"})
@RequestMapping("/payNotify/weChatPay")
@Slf4j
public class WeChatPayNotifyController {

    @Resource
    private MerchantMatchWeChatConfig matchWeChatConfig;

    @ApiOperation("支付通知")
    @PostMapping("notifyOrder/{merchantId}")
    public ResponseEntity<String> v3NotifyOrder(@PathVariable Integer merchantId, @RequestBody String requestBody) {
        log.info("支付通知notifyOrder,merchantId={},requestBody={}", merchantId, requestBody);
        Transaction wxPayNotifyDTO = getNotificationParser(merchantId, requestBody, Transaction.class);
        log.info("支付通知wxPayNotifyDTO={}", wxPayNotifyDTO);
        //TODO    业务处理，需要验证订单存在，订单是否处理，验证金额等业务操作

        log.info("支付成功处理结束返回");
        return WeChatPayV3NotifyResponse.success();
    }

    private <T> T getNotificationParser(Integer merchantId, String requestBody, Class<T> decryptObjectClass) {
        // 构造 RequestParam
        final HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        assert request != null;
        RequestParam requestParam = new RequestParam.Builder()
                .serialNumber(request.getHeader("Wechatpay-Serial"))
                .nonce(request.getHeader("Wechatpay-Nonce"))
                .signature(request.getHeader("Wechatpay-Signature"))
                .timestamp(request.getHeader("Wechatpay-Timestamp"))
                .body(requestBody)
                .build();
        log.debug("支付通知参数requestParam={}", requestParam);
        final RSAAutoCertificateConfig certificateConfig = matchWeChatConfig.getCertificateConfig(merchantId);
// 初始化 NotificationParser
        NotificationParser parser = new NotificationParser(certificateConfig);
// 以支付通知回调为例，验签、解密并转换成 Transaction
        return parser.parse(requestParam, decryptObjectClass);
    }

    @ApiOperation("退款通知")
    @PostMapping("refundOrder/{merchantId}")
    public ResponseEntity<String> refundOrder(@PathVariable Integer merchantId, @RequestBody String requestBody) {
        RefundNotification refundNotification = getNotificationParser(merchantId, requestBody, RefundNotification.class);
        log.info("收到微信退款通知:{}", refundNotification);
        //TODO    业务处理，需要验证订单存在，订单是否处理，验证金额等业务操作
        log.info("收到微信退款通知处理结束:{}", refundNotification.getOutTradeNo());
        return WeChatPayV3NotifyResponse.success();
    }


}
