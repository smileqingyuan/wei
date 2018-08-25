## 更换Druid数据源

## servlet的生命周期
Servlet 生命周期可被定义为从创建直到毁灭的整个过程。以下是 Servlet 遵循的过程：
- Servlet 通过调用 init () 方法进行初始化,只初始化一次，使用loadOnStartup, >=0 时，随着容器启动，<0时第一次访问该servlet时init
- Servlet 调用 service() 方法来处理客户端的请求。
- Servlet 通过调用 destroy() 方法终止（结束）。
- 最后，Servlet 是由 JVM 的垃圾回收器进行垃圾回收的。

## Servlet间的跳转

## 多个servelt融合成一个Action/Controller

## 框架的搭建

### 如何快速搭建开发框架

### 如何加载并读取配置文件

### 如何实现一个简单的IOC容器

### 如何加载指定的类

### 如何初始化框架