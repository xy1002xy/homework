### 日志输出规范
##### · 强制：log.info() 中避免字符串+拼接，使用占位符{}
                                                             ##### · 强制：log.error() 中，如果是异常捕捉类的，最后一定要带Exception 对象
                                                             ##### · 强制：catch 之后，必须 error 中输出 Exception
                                                             ##### · 强制：非 root节点中 logger 中，activity必须为false
                                                             ##### · 建议：使用 logstash-appender 或者使用logback 直接调用ELK 之后，需要把xml文件中 configuration 设置为 debug="true"，用户发现Logstash 异常
                                                             ##### · 建议：spring-boot 或者 spring-cloud 框架下，通过 <spring-profile> 去指定生效配置（原则上 开发 测试公用一套规则，灰度生产使用一套
                                                             ##### · 建议：不覆盖 common 下 root 级配置，让日志能够集成进ELK
                                                             ##### · 建议：自定义模板参考 common下 example 配置，自定义 logger 需
                                                             ##### · 建议：微服务业务工程中，dao 层位置固定，方便 Logger 在非生产环境统一输出 debug 级别日志