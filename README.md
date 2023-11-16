# 项目名称：JCommon
# 简介：
JCommon是一个集成了常用支付、短信等功能的 Java 工具包，致力于提供简单易用的接口封装，使开发者能够轻松在生产项目中使用。相比市面上其他支付相关的开源项目，JCommon的特色在于对支付接口的统一封装，以及对可扩展性的考虑，使得开发者可以更便捷地集成和扩展不同支付和短信服务。

# 主要特性：

1.支付接口统一封装： JCommon Integration Kit 提供了统一的支付接口，包括常见的支付宝、微信支付等，使开发者能够一致地处理不同支付方式。

2.短信功能封装： 集成了短信服务接口，包括短信发送、验证码生成等功能，为开发者提供了方便的短信集成解决方案。

3.简单易用的API： 设计简洁的API，开箱即用，降低开发者的集成难度，减少样板代码。

4.可扩展性： 架构考虑到了可扩展性，开发者可以方便地添加新的支付方式或短信服务商，以适应不同的需求。

5.详细文档和示例： 提供详细的文档和示例，帮助开发者快速上手和理解如何进行定制和扩展。


# 使用示例：
## 支付例子
```java
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
```


# 贡献者：
欢迎开发者贡献代码，提出建议和反馈问题，共同完善这个项目。

# 许可证：
Apache License 2.0

# 注意事项：
在使用该工具包前，请仔细阅读文档，了解各个支付方式的配置和使用方式。如果在使用过程中遇到问题，请查看文档或提交 issue 寻求帮助。

# 项目链接：
[JCommon](https://github.com/jsl1992/JCommon)
