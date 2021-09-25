package com.roncoo.eshop.cache.ha.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.*;
import com.roncoo.eshop.cache.ha.http.HttpClientUtils;
import com.roncoo.eshop.cache.ha.model.ProductInfo;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/19 18:27
 * 查询商品信息
 */
public class GetProductInfoCommand extends HystrixCommand<ProductInfo> {

    public static final HystrixCommandKey KEY = HystrixCommandKey.Factory.asKey("GetProductInfoCommand");

    private Long productId;

    public GetProductInfoCommand(Long productId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProductInfoService"))
                .andCommandKey(KEY)
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetProductInfoPool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        // 设置线程池大小
                        .withCoreSize(15)
                        // 设置的是你的等待队列，缓冲队列的大小
                        .withMaxQueueSize(12)
                        .withQueueSizeRejectionThreshold(8)
                )
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerEnabled(true)
                        // 设置一个rolling window，滑动窗口中，最少要有多少个请求时，才触发开启短路,默认是20
                        .withCircuitBreakerRequestVolumeThreshold(30)
                        // 设置异常请求量的百分比，当异常请求达到这个百分比时，就触发打开短路器，默认是50，也就是50%
                        .withCircuitBreakerErrorThresholdPercentage(40)
                        // 设置在短路之后，需要在多长时间内直接reject请求，然后在这段时间之后，再重新导holf-open状态，尝试允许请求通过以及自动恢复，默认值是5000毫秒
                        .withCircuitBreakerSleepWindowInMilliseconds(3000)
                        // 如果请求放等待队列中时间太长了，直接就会timeout，等不到去线程池里执行了
                        .withExecutionTimeoutInMilliseconds(5000)
                        // fallback，sempahore限流，30个，避免太多的请求同时调用fallback被拒绝访问
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(30)
                )
        );
        this.productId = productId;
    }

    @Override
    protected ProductInfo run() throws Exception {
        System.out.println("调用接口,查询商品数据,productId=" + productId);
        if (productId.equals(-1L)) {
            throw new Exception();
        }
        if (productId.equals(-2L)) {
            Thread.sleep(2000);
        }
        String url = "http://127.0.0.1:8082/getProductInfo?productId=" + productId;
        String response = HttpClientUtils.sendGetRequest(url);
        System.out.println(response);
        return JSONObject.parseObject(response, ProductInfo.class);
    }

//    @Override
//    protected String getCacheKey() {
//        return "product_info_" + productId;
//    }
//
//    public static void flushCache(Long productId) {
//        HystrixRequestCache.getInstance(KEY, HystrixConcurrencyStrategyDefault.getInstance())
//                .clear("product_info_" + productId);
//    }


    @Override
    protected ProductInfo getFallback() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setName("降级商品");
        return productInfo;
    }
}
