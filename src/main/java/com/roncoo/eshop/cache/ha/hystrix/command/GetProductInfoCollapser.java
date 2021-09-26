package com.roncoo.eshop.cache.ha.hystrix.command;

import com.alibaba.fastjson.JSONArray;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.roncoo.eshop.cache.ha.http.HttpClientUtils;
import com.roncoo.eshop.cache.ha.model.ProductInfo;

import java.util.Collection;
import java.util.List;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/26 21:46
 */
public class GetProductInfoCollapser extends HystrixCollapser<List<ProductInfo>, ProductInfo, Long> {

    private Long productId;

    public GetProductInfoCollapser(Long productId) {
        this.productId = productId;
    }

    @Override
    public Long getRequestArgument() {
        return productId;
    }

    @Override
    protected HystrixCommand<List<ProductInfo>> createCommand(Collection<CollapsedRequest<ProductInfo, Long>> requests) {
        return new BatchCommand(requests);
    }

    @Override
    protected String getCacheKey() {
        return "product_info_" + productId;
    }

    @Override
    protected void mapResponseToRequests(List<ProductInfo> productInfos, Collection<CollapsedRequest<ProductInfo, Long>> requests) {
        int count = 0;
        for (CollapsedRequest<ProductInfo, Long> request : requests) {
            request.setResponse(productInfos.get(count++));
        }
    }

    private static final class BatchCommand extends HystrixCommand<List<ProductInfo>> {

        public final Collection<CollapsedRequest<ProductInfo, Long>> requests;

        protected BatchCommand(Collection<CollapsedRequest<ProductInfo, Long>> requests) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProductInfoService"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetProductInfosCollapserBatchCommand")));
            this.requests = requests;
        }

        @Override
        protected List<ProductInfo> run() {
            // 将一个批次内的商品id给拼接起来
            StringBuilder paramsBuilder = new StringBuilder();
            for (CollapsedRequest<ProductInfo, Long> request : requests) {
                paramsBuilder.append(request.getArgument()).append(",");
            }
            String params = paramsBuilder.toString();
            params = params.substring(0, params.length() - 1);

            // 在这里,我们可以将多个商品id合并在一个batch内,直接发送一次网络请求
            String url = "http://localhost:8082/getProductInfos?productIds=" + params;
            String response = HttpClientUtils.sendGetRequest(url);
            List<ProductInfo> productInfos = JSONArray.parseArray(response, ProductInfo.class);
            for (ProductInfo productInfo : productInfos) {
                System.out.println("BatchCommand内部,productInfo=" + productInfo);
            }
            return productInfos;
        }
    }
}
