**项目背景**
=========================

- 对于任何一个后台系统都非常重要，随着业务的发展，不同阶段的权限系统又面临着不同设计和重构，企业中不同业务系统权限控制方式不统一，维护和实现成本增加，开发对接效率低，实现方式多样化，用户各种权限分配需求不能满足，实现起来非常蹩脚，这样只会造成恶性循环，最终可能影响业务发展甚至推倒重做，我们极为迫切的需要一个通用权限系统，界面美观、安全性高、便于管理使用、不侵入业务、又可以支持并发量高个一个通用权限系统，那么该系统应运而生，解决企业开发中遇到的痛点和难点，也是小伙伴们学习源码、设计模式、常用微服务架构等经典知识的平台和途径！

工程结构
=========================

``` 
zsh-cloud
├
├── zsh-cloud-business -- 业务服务父级文件夹
├     ├
├     └── zsh-cloud-weixin-server -- 微信服务
├
├── zsh-cloud-common -- 公共工具工程
├     ├
├     ├── zsh-cloud-common-core -- 核心包
├     ├
├     ├── zsh-cloud-common-exception -- 异常
├     ├
├     ├── zsh-cloud-common-j2cache -- j2cache缓存
├     ├
├     ├── zsh-cloud-common-log -- 操作日志
├     ├
├     ├── zsh-cloud-common-mybatis -- mybatis
├     ├
├     ├── zsh-cloud-common-redis -- redis
├     ├
├     ├── zsh-cloud-common-swagger -- swagger
├     ├
├     ├── zsh-cloud-common-tenant -- 租户
├     ├
├     ├── zsh-cloud-common-web -- web
├     ├
├     └── zsh-cloud-common-weixin -- 微信
├
├── zsh-cloud-dependencies -- 包依赖工程
├
├── zsh-cloud-ops -- 运维工程
├     ├
├     ├── zsh-cloud-auth-server -- 认证
├     ├
├     └── zsh-cloud-gateway-server -- 网关
├
├── zsh-cloud-support -- 基础工程
├     ├
├     ├── zsh-cloud-system-api -- api
├     ├
├     └── zsh-cloud-system-server -- 基础服务
├
└──  
```

环境要求
=========================

- JDK ： 1.8 +
- Maven： 3.3 +
  http://maven.apache.org/download.cgi
- Mysql： 5.6.0 +
  https://downloads.mysql.com/archives/community
- Redis： 4.0 +
  https://redis.io/download
- Nacos： 1.1.4
  https://github.com/alibaba/nacos/releases





