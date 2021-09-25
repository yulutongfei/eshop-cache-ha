package com.roncoo.eshop.cache.ha.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.roncoo.eshop.cache.ha.cache.local.LocationCache;
import com.roncoo.eshop.cache.ha.http.HttpClientUtils;
import com.roncoo.eshop.cache.ha.model.ProductInfo;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/19 18:27
 * 查询商品信息
 */
public class GetProductInfoCommand extends HystrixCommand<ProductInfo> {

    public static final HystrixCommandKey KEY = HystrixCommandKey.Factory.asKey("GetProductInfoGroup");

    private Long productId;

    public GetProductInfoCommand(Long productId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetProductInfoGroup"))
                .andCommandKey(KEY));
        this.productId = productId;
    }
    @Override
    protected ProductInfo run() {
        String url = "http://127.0.0.1:8082/getProductInfo?productId=" + productId;
        String response = HttpClientUtils.sendGetRequest(url);
        System.out.println(response);
        System.out.println("调用接口,查询商品数据,productId=" + productId);
        return JSONObject.parseObject(response, ProductInfo.class);
    }

    @Override
    protected String getCacheKey() {
        return "product_info_" + productId;
    }

    public static void flushCache(Long productId) {
        HystrixRequestCache.getInstance(KEY, HystrixConcurrencyStrategyDefault.getInstance())
                .clear("product_info_" + productId);
    }
}
