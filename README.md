# car-OCR
基于机器学习和OCR的车牌识别系统 @fujunhao

####1、运行环境
服务端：jdk1.8、OpenCV 2.4.9
客户端：能上网的浏览器即可（windows、linux、android不限）
#### 2、 开发环境
操作系统：windows7 64位
编程语言：Java(要求至少jdk1.8以上)
开发工具：Eclipse
第三方库：OpenCV2.4.9  、Spring Boot
Maven依赖以及版本要求：
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.bytedeco</groupId>
			<artifactId>javacv</artifactId>
			<version>0.11</version>
		</dependency>
		<dependency>
			<groupId>org.bytedeco</groupId>
			<artifactId>javacpp</artifactId>
			<version>0.11</version>
		</dependency>
```
####3、OpenCV安装说明（windows下）
- 详细可以参考：http://blog.csdn.net/morewindows/article/details/8225783/
- 下载好opencv.exe，下载地址：https://sourceforge.net/projects/opencvlibrary/
![Alt text](./1496751598264.png)
- 下载好后，直接安装
![Alt text](./1496751638565.png)
- 将OpenCV文件夹里的jar包添加到项目lib中
 ![Alt text](./1496751936596.png)
#### 4、将Car-OCR项目导入Eclipse工作空间里
![Alt text](./1496752007607.png)
![Alt text](./1496752028489.png)
注意：这个项目使用Maven管理的，所以要在开发环境中配置好Maven
#### 4、添加Spring Boot依赖（用作web接口开发）
![Alt text](./1496752178465.png)
#### 5、添加好依赖后，如果项目不报错就可以运行启动了
- 启动类 src/org.easypr/MainApplication
- 启动方法 运行MainApplication里的主方法即可
- 控制台的日志正常输出，不报错即可
![Alt text](./1496752355886.png)

#### 6、系统测试
- 访问主页
http://localhost:8080/ (默认8080端口，如果需要部署到公网上，改成80端口即可)
![Alt text](./1496752428310.png)
-  图片上传
![Alt text](./1496752517350.png)
- 车牌识别
控制台输出识别日志如下：
![Alt text](./1496752566725.png)
结果返回形式为json
![Alt text](./1496752585733.png)
```json
{"status":201,"response":["川C2888B"],"date":"2017-06-06 20:35:36"}
```
7、 接口参数说明
- 车牌识别服务接口地址：
http://localhost:8080/classify_upload
- 车牌识别服务返回结果：
```json
{"status":201,"response":["川C2888B"],"date":"2017-06-06 20:35:36"}
```
- 字段说明
```xml
status: 201 表示成功调用接口；-1 表示调用接口失败
response: 识别结果数组（返回的识别为可能的字符top2）
date:代表当前时间戳
```
