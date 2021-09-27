package com.roncoo.eshop.cache.ha.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.roncoo.eshop.cache.ha.model.ProductInfo;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/27 00:31
 */
public class GetProductInfoFromMySQLCommand extends HystrixCommand<ProductInfo> {

    private Long productId;

    public GetProductInfoFromMySQLCommand(Long productId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProductInfoService")));
        this.productId = productId;
    }

    @Override
    protected ProductInfo run() throws Exception {
        String json = "{\n" +
                "  \"id\": " + productId + ",\n" +
                "  \"name\": \"iphone7手机\",\n" +
                "  \"price\": 5599,\n" +
                "  \"pictureList\": \"a.jpg,b.jpg\",\n" +
                "  \"specification\": \"iphone7的规格\",\n" +
                "  \"service\": \"iphone7的售后服务\",\n" +
                "  \"color\": \"红色,白色,黑色\",\n" +
                "  \"size\": 5.5,\n" +
                "  \"shopId\": 1,\n" +
                "  \"modifiedTime\": \"2021-06-28 12:01:00\",\n" +
                "  \"cityId\": 1,\n" +
                "  \"brandId\": 1\n" +
                "}";
        return JSONObject.parseObject(json, ProductInfo.class);
    }
}
