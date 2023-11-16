package com.github.ji.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantMatchVO {


    @ApiModelProperty(value = "商户号")
    private String mchId;

    @ApiModelProperty(value = "商户APIV3密钥")
    private String apiKey;

    @ApiModelProperty(value = "商户API私钥路径")
    private String privatekeyPath;

    @ApiModelProperty(value = "商户证书序列号")
    private String merchantSerialNumber;


}
