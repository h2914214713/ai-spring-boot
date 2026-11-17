# ai-spring-boot

一个基于 **Spring Boot 3.2.5 + Java 17** 的示例项目，包含三个 REST 接口（用户列表、用户详情、创建用户）。

## 技术栈

| 组件 | 版本 |
| --- | --- |
| JDK | 17 |
| Spring Boot | 3.2.5 |
| Maven | 3.8+ |

## 快速开始

```bash
# 编译打包
mvn clean package

# 运行
mvn spring-boot:run
# 或
java -jar target/ai-spring-boot-0.0.1-SNAPSHOT.jar
```

服务默认监听 `http://localhost:8080`。

## 接口说明

### 1. 查询用户列表

```http
GET /api/users
```

```bash
curl http://localhost:8080/api/users
```

响应示例：

```json
[
  { "id": 1, "name": "张三", "email": "zhangsan@example.com" },
  { "id": 2, "name": "李四", "email": "lisi@example.com" }
]
```

### 2. 查询用户详情

```http
GET /api/users/{id}
```

```bash
curl http://localhost:8080/api/users/1
```

用户不存在时返回 `404`：

```json
{
  "timestamp": "2026-11-17T08:30:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "用户不存在, id=100"
}
```

### 3. 创建用户

```http
POST /api/users
Content-Type: application/json
```

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"王五","email":"wangwu@example.com"}'
```

成功返回 `201 Created`，并在 `Location` 响应头中带上新资源地址：

```json
{ "id": 3, "name": "王五", "email": "wangwu@example.com" }
```

参数校验失败返回 `400`，例如邮箱格式错误：

```json
{
  "timestamp": "2026-11-17T08:31:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "email: email 格式不正确"
}
```

## 项目结构

```
src/main/java/com/example/demo
├── DemoApplication.java              # 启动类
├── controller/UserController.java    # 三个 REST 接口
├── service/UserService.java          # 内存存储的业务逻辑
├── model/User.java                   # 用户实体（record）
├── dto/UserCreateRequest.java        # 创建请求体 + 校验注解
└── exception/                        # 全局异常处理
```

## 说明

数据存储使用 `ConcurrentHashMap` 内存实现，重启后数据清空，仅用于演示接口定义与分层结构。
