package com.fqh.userserver.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * swagger2地址打印配置
 *
 * @author fqh
 * @Description 控制台输出 Swagger 接口文档地址
 * @date 2022/08/14
 */
@Component
@Slf4j
public class SwaggerPrintConfig implements ApplicationListener<WebServerInitializedEvent> {
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            //获取IP
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            //获取端口号
            int port = event.getWebServer().getPort();
            //获取应用名
            String applicationName = event.getApplicationContext().getApplicationName();
            log.info("项目启动启动成功！接口文档地址: http://"+hostAddress+":"+event.getWebServer().getPort()+applicationName+"/swagger-ui.html");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

