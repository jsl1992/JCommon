package com.github.ji.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付 properties
 *
 * @author jisl on 2023/11/16 11:17
 **/
@Data
@ConfigurationProperties(prefix = "wxpay")
@Configuration
public class WxPayProperties {
    /**
     * appid
     */
    private String appid;

    /**
     * 微信支付通知地址
     */
    private String domainUrl;


}
