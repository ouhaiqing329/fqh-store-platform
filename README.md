# fqh-store-platform

微服务架构

springBoot+Nacos+Gateway+security+mybatis-plus+redis

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

