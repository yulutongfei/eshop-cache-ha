package com.roncoo.eshop.cache.ha.hystrix.command;

import com.netflix.hystrix.*;
import com.roncoo.eshop.cache.ha.cache.local.BrandCache;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/25 14:42
 */
public class GetBrandNameCommand extends HystrixCommand<String> {

    private Long brandId;


    public GetBrandNameCommand(Long brandId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BrandInfoService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetBrandNameCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetBrandInfoPool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(15)
                        .withQueueSizeRejectionThreshold(10))
                // 这个参数设置了HystrixCommand.getFallback()最大允许的并发请求数量,默认值是10
                // 也是通过semaphore信号量的机制去限流
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(10))
        );
        this.brandId = brandId;
    }

    @Override
    protected String run() throws Exception {
        // 如果调用一个品牌服务的接口
        // 如果调用失败了,报错了,那么就会去调用fallback降级机制
        throw new Exception();
    }

    @Override
    protected String getFallback() {
        return BrandCache.getBrandName(brandId);
    }
}
