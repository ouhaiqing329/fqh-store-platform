# fqh-store-platform

微服务架构

springBoot+Nacos+Gateway+security+mybatis-plus+redis+elasticsearch+feign

服务结构：
- 1.公共组件 commit-utils（使用本地仓库存储/git install打包上传到本地仓库即可）
- 2.订单服务 order-server
- 3.商城服务 store-server
- 4.用户服务 user-server
- 5.认证服务 auth-server  应用程序统一入口


项目地址：
https://github.com/ouhaiqing329/fqh-store-platform/tree/master


rocketmq相关：
- 1.rocketmq控制台打包命令：mvn clean package -Dmaven.test.skip=true 
- 2.rocketmq控制台启动命令：nohup java -jar rocketmq-dashboard-1.0.1-SNAPSHOT.jar &

elasticsearch相关：
- 1.elasticsearch默认地址：http://tx:9200/9300
- 2.kibana默认地址： http://ali:5601

jasypt相关：
* 1.jasypt加密盐使用环境变量存储，启动项目需要加入环境变量JASYPT_PASSWORD=xxx 
* 2.例如：idea启动需要在Environment variables处添加：JASYPT_PASSWORD=xxx
* 3.java -jar -JASYPT_PASSWORD=xxx xxx.jar