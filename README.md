# Spring Boot Practice Project

這是一個使用 Spring Boot 框架的練習專案，整合了多個常用的 Spring 生態系統組件。

## 技術棧

- **核心框架**: Spring Boot 3.5.3
- **Java 版本**: 17
- **資料庫**: PostgreSQL
- **視圖模板**: Thymeleaf
- **安全框架**: Spring Security
- **開發工具**: Lombok, Spring Boot DevTools
- **構建工具**: Maven

## 功能特點

- RESTful API 開發
- 使用 JPA 進行資料庫操作
- 基於 Thymeleaf 的前端頁面
- 整合 Spring Security 進行安全認證
- 表單驗證功能
- 響應式網頁設計

## 專案結構

```
SpringBootPractice/
├── src/
│   ├── main/
│   │   ├── java/com/practice/        # Java 源代碼
│   │   └── resources/                # 資源文件
│   │       ├── static/               # 靜態資源 (CSS, JS, images)
│   │       ├── templates/            # Thymeleaf 模板
│   │       └── application.properties # 應用程序配置
│   └── test/                         # 測試代碼
├── .gitignore
├── pom.xml                           # Maven 配置
└── README.md
```

## 環境要求

- JDK 17 或更高版本
- Maven 3.6 或更高版本
- PostgreSQL 數據庫

## 快速開始

1. 克隆倉庫
   ```bash
   git clone [repository-url]
   cd SpringBootPractice
   ```

2. 配置數據庫
   - 創建 PostgreSQL 數據庫
   - 在 `application.properties` 中配置數據源

3. 構建並運行
   ```bash
   mvn spring-boot:run
   ```

4. 訪問應用
   - 瀏覽器訪問: http://localhost:8080

## 開發指南

- 使用 `mvn clean install` 清理並安裝依賴
- 使用 `mvn spring-boot:run` 啟動開發服務器
- 開發時啟用了 DevTools，支持熱部署

By FVGB4736
