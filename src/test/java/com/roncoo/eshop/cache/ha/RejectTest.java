package com.roncoo.eshop.cache.ha;

import com.roncoo.eshop.cache.ha.http.HttpClientUtils;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/25 17:35
 */
public class RejectTest {
    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 25; i++) {
            new TestThread(i).start();
        }
    }

    private static class TestThread extends Thread {
        private int index;

        public TestThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            String response = HttpClientUtils.sendGetRequest("http://127.0.0.1:8081/getProductInfo?productId=-2");
            System.out.println("第" + (index + 1) + "次请求,结果为: " + response);
        }
    }
}
