package com.silv.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;

/**
 * 每次启动项目自动打印swagger页面的地址
 */
@SpringBootConfiguration
public class AutoStartSwaggerPageInDefaultBrowser implements CommandLineRunner {

    //注入项目的端口号
    @Value("${server.port}")
    private String port;

    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("======================== swagger页面地址 ========================");
        System.out.println("http://localhost:" + port + "/index.html");
        System.out.println("======================== swagger页面地址 ========================");
        /*try {
            Runtime.getRuntime().exec("cmd /c start http://localhost:" + port + contextPath + "/index.html");
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }
}
