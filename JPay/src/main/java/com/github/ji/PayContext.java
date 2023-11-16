package com.github.ji;


import com.github.ji.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 支付方式的PayContext，自动加入到这个PayContext
 */
@Slf4j
@Component
public class PayContext {

    private Map<Integer, PayService> payServiceMap;

    // spring中，在使用@Autowired注解注入list集合的时候，并不会根据List类型去容器中查找，而是根据list集合的元素类型，从spring容器中找到所有的实现类，放在list集合中，然后注入到bean中
    @Resource
    private List<PayService> payServiceList;


    @PostConstruct
    public void buildMap() {
//        将来完善下，可以加入是否禁用等
        payServiceMap = payServiceList.stream()
                .collect(Collectors.toMap(operationStrategy -> operationStrategy.getPayCode().getCode(), Function.identity()));
    }

    public PayService getPayService(Integer code) {
        if (!payServiceMap.containsKey(code)) {
            throw new RuntimeException("没有该支付方式");
        }
        return payServiceMap.get(code);
    }


}
