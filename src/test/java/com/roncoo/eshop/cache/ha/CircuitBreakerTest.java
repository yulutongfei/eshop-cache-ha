package com.roncoo.eshop.cache.ha;

import com.roncoo.eshop.cache.ha.http.HttpClientUtils;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/25 15:27
 * 短路器测试类
 */
public class CircuitBreakerTest {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 15; i++) {
            String response = HttpClientUtils.sendGetRequest("http://127.0.0.1:8081/getProductInfo?productId=1");
            System.out.println("第" + (i + 1) + "次请求,结果为: " + response);
        }
        for (int i = 0; i < 25; i++) {
            String response = HttpClientUtils.sendGetRequest("http://127.0.0.1:8081/getProductInfo?productId=-1");
            System.out.println("第" + (i + 1 + 15) + "次请求,结果为: " + response);
        }
        System.out.println("尝试等待一个时间窗口5秒钟");
        Thread.sleep(5000);
        // 等待了5s后,时间窗口统计了,发现异常比例太多了,就短路了
        for (int i = 0; i < 15; i++) {
            String response = HttpClientUtils.sendGetRequest("http://127.0.0.1:8081/getProductInfo?productId=-1");
            System.out.println("第" + (i + 1 + 40) + "次请求,结果为: " + response);
        }
        for (int i = 0; i < 15; i++) {
            String response = HttpClientUtils.sendGetRequest("http://127.0.0.1:8081/getProductInfo?productId=1");
            System.out.println("第" + (i + 1 + 55) + "次请求,结果为: " + response);
        }
        System.out.println("尝试等待5秒钟");
        Thread.sleep(5000);
        for (int i = 0; i < 10; i++) {
            String response = HttpClientUtils.sendGetRequest("http://127.0.0.1:8082/getProductInfo?productId=1");
            System.out.println("第" + (i + 1) + "次请求,结果为: " + response);
        }
    }
}
