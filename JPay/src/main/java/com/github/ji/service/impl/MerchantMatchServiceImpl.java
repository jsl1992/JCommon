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
//        实际需要从数据库读取，这边直接返回。方便启动项目
        return MerchantMatchVO.builder().build();
    }
}
