package com.github.ji.service.impl;


import com.github.ji.service.IMerchantMatchService;
import com.github.ji.vo.MerchantMatchVO;
import org.springframework.stereotype.Service;

/**
 * 门店对应商户Service业务层处理
 *
 * @author jisl on 2023/11/16 10:22
 **/
@Service
public class MerchantMatchServiceImpl implements IMerchantMatchService {


    @Override
    public MerchantMatchVO selectByMerchantId(Integer merchantId) {
//       这边使用的是多商户场景，这边是商户的一些支付信息。因为是多商户，需要从数据库读取。
//        如果实际项目使用的是单商户，那么可以将商户的支付信息，写在配置文件里。
//        比如要使用微信支付，那么需要配置好相关的信息（mchId（商户号），apiKey（商户APIV3密钥），privatekeyPath（商户API私钥路径），merchantSerialNumber（商户证书序列号））
//        return  merchantMatchMapper.selectByMerchantId(merchantId);
        return MerchantMatchVO.builder().build();
    }
}
