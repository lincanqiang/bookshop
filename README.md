# 项目介绍
基于SpringBoot和Vue开发的网上书店，实现前后端分离。

## 在线预览
* [前台](http://sumuzhe.top/)
* [后台](http://sumuzhe.top/admin)（账号：admin,密码：123456）

## 技术栈
### 后端
* Spring Boot
* Spring Data Jpa
* MySQL
* Redis
* Shiro
### 前端
* Vue
* ElementUI
* Axios

### 三方服务
* 阿里云对象存储

# 项目部署
**bookshop文件夹为打包了前端静态资源的后端项目文件，可以独立运行，端口为8443。**
1. clone项目到本地。（本项目使用JDK1.8）
<br/>`git clone git@github.com:lincanqiang/bookshop.git`
2. 在MySQL中创建数据库，并导入bookshop.sql文件。修改application.properties中的配置。（本项目使用MySql5.7,不同版本或许会带来兼容问题）
3. 安装Redis,使用默认配置即可。
4. 启动项目，访问8443端口。
5. 可选：修改/config/AliYunOssConfig，填入你自己的阿里云图床配置。（非必要，如不配置，上传图片功能将无法使用）

**bookshop-vue为前端项目文件，单独运行无法获取静态资源。端口为8080，跨域配置已完成。**
命令行中进入项目目录
1. 安装依赖
<br/>`npm intsall` 
2.运行 
<br/>`npm run serve`

