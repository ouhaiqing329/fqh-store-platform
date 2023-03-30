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


SSO单点登录是指系统经过一次认证，其他系统接口共享登录状态
使用Spring security + jwt 做去中心化、无状态登录

具体方案：
	1.用户请求到达gateway网关，认证请求则直接放行，授权请求需要调用认证服务接口进行授权
	2.认证服务集成security和jwt提供认证接口，并使用过滤器拦截token，验证token是否过期，过期则验证Cookie中的token是否过期，两个token都过期则放行进行认证，续签token没有过期则生成
新的token返回给网关，
	3.授权
