package com.roncoo.eshop.cache.ha;

import com.roncoo.eshop.cache.ha.http.HttpClientUtils;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/27 00:17
 */
public class MultiLevelFallbackTest {

    public static void main(String[] args) {
        String response = HttpClientUtils.sendGetRequest("http://127.0.0.1:8081/getProductInfo?productId=-1");
        System.out.println("第1次请求,结果为: " + response);

        response = HttpClientUtils.sendGetRequest("http://127.0.0.1:8081/getProductInfo?productId=-2");
        System.out.println("第2次请求,结果为: " + response);
    }
}
