package com.github.ji.jpay.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 门店对应商户对象 merchant_match
 *
 * @author jisl on 2023/11/16 10:19
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MerchantMatch {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 门店id
     */
    private Integer merchantId;

    /**
     * 商户id
     */
    private String mchId;

    /**
     * 商户key
     */
    private String mchKey;

    /**
     * $column.columnComment
     */
    private String appKey;

    /**
     * $column.columnComment
     */
    private String mchSerialNo;

    /**
     * $column.columnComment
     */
    private String privateKeyUrl;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date addTime;

    /**
     * 逻辑删除
     */
    private Integer deleted;

}
