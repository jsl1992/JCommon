package com.github.ji.jpay.service;


import com.github.ji.jpay.vo.MerchantMatchVO;

/**
 * 门店对应商户Service接口
 *
 * @author jisl on 2023/11/16 11:18
 **/
public interface IMerchantMatchService {

    /**
     * 门店对应商户
     *
     * @param merchantId merchantId
     * @return com.github.ji.vo.MerchantMatchVO
     * @author jisl on 2023/11/16 11:18
     **/
    MerchantMatchVO selectByMerchantId(Integer merchantId);
}
