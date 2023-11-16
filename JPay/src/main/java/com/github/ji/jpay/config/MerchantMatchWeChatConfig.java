package com.github.ji.jpay.config;


import com.github.ji.jpay.vo.MerchantMatchVO;
import com.github.ji.jpay.service.IMerchantMatchService;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class MerchantMatchWeChatConfig {

    @Resource
    private IMerchantMatchService merchantMatchService;


    /**
     * 本地缓存RSAAutoCertificateConfig, key 是微信支付的商户 mchId
     **/
    private static final Map<String, RSAAutoCertificateConfig> CONFIG_MAP = new ConcurrentHashMap<>();


    /**
     * 获取微信支付的RSAAutoCertificateConfig
     *
     * @param merchantMatchVO 商户配置
     * @return com.wechat.pay.java.core.RSAAutoCertificateConfig
     * @author jishenglong on 2023/4/19 14:10
     **/
    public RSAAutoCertificateConfig getCertificateConfig(MerchantMatchVO merchantMatchVO) {
        if (Objects.isNull(merchantMatchVO)) {
            throw new RuntimeException("商户还未配置支付账号，请联系超管处理");
        }
//        多个模式
        if (!CONFIG_MAP.containsKey(merchantMatchVO.getMchId())) {
            initMerchantMatchConfig(merchantMatchVO);
        }
        return CONFIG_MAP.get(merchantMatchVO.getMchId());
    }

    /**
     * 获取微信支付的RSAAutoCertificateConfig
     *
     * @param merchantId 门店id
     * @return com.wechat.pay.java.core.RSAAutoCertificateConfig
     * @author jishenglong on 2023/4/19 14:10
     **/
    public RSAAutoCertificateConfig getCertificateConfig(Integer merchantId) {
        final MerchantMatchVO merchantMatchVO = merchantMatchService.selectByMerchantId(merchantId);
        return getCertificateConfig(merchantMatchVO);
    }

    /**
     * 初始化微信支付方法（因为要单例所以需要 synchronized）
     *
     * @param merchantMatchVO 商户支付配置
     * @author jishenglong on 2023/4/19 14:08
     **/
    private synchronized void initMerchantMatchConfig(MerchantMatchVO merchantMatchVO) {
        if (!CONFIG_MAP.containsKey(merchantMatchVO.getMchId())) {
            RSAAutoCertificateConfig config = new RSAAutoCertificateConfig.Builder()
                    .merchantId(merchantMatchVO.getMchId())
                    .merchantSerialNumber(merchantMatchVO.getMerchantSerialNumber())
                    .apiV3Key(merchantMatchVO.getApiKey())
                    .privateKeyFromPath(merchantMatchVO.getPrivatekeyPath())
                    .build();
            CONFIG_MAP.put(merchantMatchVO.getMchId(), config);
        }
    }
}
