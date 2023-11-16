package com.github.ji.controller;

import com.github.ji.PayContext;
import com.github.ji.core.CommonResult;
import com.github.ji.dto.OrderPayDTO;
import com.github.ji.dto.PayDTO;
import com.github.ji.entity.Order;
import com.github.ji.service.IMerchantMatchService;
import com.github.ji.service.PayService;
import com.github.ji.vo.PayVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jisl on 2023/11/16 10:44
 **/
@RestController
@RequestMapping("/pay")
@Api(tags = {"支付管理"})
@Slf4j
public class PayController {


    @Resource
    private IMerchantMatchService merchantMatchService;

    @Resource
    private PayContext payContext;

    @ApiOperation("订单支付")
    @PostMapping("/orderPay")
    public CommonResult<PayVO> orderPay(@Validated @RequestBody OrderPayDTO orderDTO) {
        final PayService payService = payContext.getPayService(orderDTO.getPayCode());
        Order order = new Order();
        final PayDTO payDTO = PayDTO.builder()
                .description(order.getDescription()).outTradeNo(order.getOutTradeNo())
                .openid(orderDTO.getOpenid()).amount(order.getAmount())
                .payCode(orderDTO.getPayCode()).merchantId(order.getMerchantId())
                .build();
        final PayVO payVO = payService.pay(payDTO);
        return CommonResult.success(payVO);
    }


}
