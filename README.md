# Getting Started

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── haier
│   │   │           └── tms
│   │   │               ├── TmsApplication.java // 项目的入口, 可以直接运行这个类运行项目
│   │   │               ├── common // 定义公共常量的地方
│   │   │               │   ├── BinConstant.java
│   │   │               │   └── Constant.java
│   │   │               ├── controller // Web的路由控制器
│   │   │               │   └── BinPackingController.java
│   │   │               ├── domain // 实现装箱业务相关需要用到的类
│   │   │               │   ├── Bin.java
│   │   │               │   ├── Item.java
│   │   │               │   └── Packer.java
│   │   │               ├── request // 项目接受的输入
│   │   │               │   ├── BinPickingGood.java
│   │   │               │   ├── BinPickingOrder.java
│   │   │               │   ├── BinPickingParam.java // 入口
│   │   │               │   ├── BinPickingRestriction.java
│   │   │               │   └── BinPickingVehicleModel.java
│   │   │               ├── response // 项目返回值
│   │   │               │   ├── BinPickingResult.java // 入口
│   │   │               │   ├── BinPickingResultGood.java
│   │   │               │   ├── BinPickingResultStep.java
│   │   │               │   └── BinPickingResultTrain.java
│   │   │               ├── service  // 这里实现装箱业务相关的方法
│   │   │               │   ├── AlgorithmService.java // 定义接口
│   │   │               │   ├── BinService.java
│   │   │               │   ├── PackingService.java
│   │   │               │   └── impl // 具体实现
│   │   │               │       ├── AlgorithmServiceImpl.java
│   │   │               │       ├── BinServiceImpl.java
│   │   │               │       └── PackingServiceImpl.java
│   │   │               └── util  // 这里放置公共工具类
│   │   │                   ├── AlgorithmCache.java
│   │   │                   └── JsonUtil.java
│   │   └── resources
│   │       ├── application.properties  // 项目配置文件
```

### start project

1. 运行项目:

    run command: `mvn spring-boot:run`

2. 打包项目:

    clean and package project:
    
    `mvn package clean -Dmaven.test.skip=true`
    
    repackage project:
    
    `mvn package -Dmaven.test.skip=true`
    
命令行一键发送请求:

curl -H "Content-Type:application/json" -H "Data_Type:msg" -X POST --data "$(cat test_data1.json)" http://localhost:80/api/binpicking

测试数据位于 /tms-bin-packing/src/main/resources/test_data 之中

### **centos** 安装 maven:

1. `wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo`

2. `yum -y install apache-maven`
