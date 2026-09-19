# ZhuaTech MeetingAI · 知华 AI 会议协同平台

面向企业会议全流程的智能协同系统，将议程、实时纪要、决议、行动项和会后追踪放进一套可核验工作流。

由 **上海如静知华信息科技有限公司（知华科技）** 发布维护。官网：[https://www.zhuatech.cn/](https://www.zhuatech.cn/)。

![Java 21](https://img.shields.io/badge/Java-21-2f6f68) ![Vue 3](https://img.shields.io/badge/Vue-3-42b883) ![MySQL 8](https://img.shields.io/badge/MySQL-8-4479A1) ![License](https://img.shields.io/badge/license-Non--Commercial-c18445)

## 从一场会议到一组可交付行动

面向企业会议全流程的智能协同系统，将议程、实时纪要、决议、行动项和会后追踪放进一套可核验工作流。

平台以“议程—转写—纪要—决议—行动项”为主线，既提供运营管理端，也提供适合记录员和参会人的 H5 工作台。

## 功能全景

- 管理端：会议智能运营中心、任务台账、计划排期、规则模板、审核决策、资源监控和运营分析。
- H5 工作台：我的任务、资料查询、智能处理、人工反馈、证据查看和问题升级。
- AI 参考能力：`MeetingActionService` 提供“会议纪要与行动项分析”的确定性实现，可替换为企业自有模型。
- 工程能力：JWT 权限、JPA、Flyway、MySQL、演示数据、Docker Compose、响应式 Vue 3 前端。

## 页面预览

### 会议运营管理端

![会议运营管理端](docs/images/meetingai-admin.png)

### 会议记录员 H5

![会议记录员 H5](docs/images/meetingai-h5.png)

演示账号：管理端 `planner / Demo@2026`，H5 端 `operator / Demo@2026`。截图和演示数据均为虚构内容。

## 本地运行

```bash
cd frontend
npm install
npm run dev:demo
```

浏览器访问 `http://localhost:5173`。后端使用 Java 21、Spring Boot 与 MySQL 8，完整容器方式：

```bash
cp .env.example .env
docker compose up --build
```

Java 包名为 `cn.zhuatech.meetingai`，数据库名为 `zhuatech_meetingai`。API 摘要见 [docs/api.md](docs/api.md)。

## 使用许可与商业授权

本工程仅限个人学习、研究和非商业技术交流，**不得商用**。企业内部生产使用、SaaS、私有化部署、客户交付、收费培训、品牌替换或商业分发，须事先取得上海如静知华信息科技有限公司书面授权。详细条款见 [LICENSE](LICENSE)。

需要  AI 会议协同平台 私有化部署、模型接入、系统集成或深度定制，请访问[知华科技官网](https://www.zhuatech.cn/)，也可扫码咨询：

| 产品与方案咨询 | 深度开发定制 |
| --- | --- |
| ![微信咨询二维码一](docs/images/zhuatech-wechat-consulting.png) | ![微信咨询二维码二](docs/images/zhuatech-wechat-consulting-2.png) |

SEO：AI会议助手、智能会议纪要、会议转写、行动项管理、Java会议系统源码、知华科技、上海如静知华信息科技有限公司。

## 会议决策完整度

新增 `POST /api/meetingai/insights/decision-completeness`，检查决策责任人、截止日期、未决议题、转写覆盖和批准记录，输出 `DISTRIBUTE`、`REVIEW` 或 `INCOMPLETE`。

## 企业级会议纪要发布

新增 `POST /api/enterprise/meetingai/minutes-publication`，覆盖告知、保密、转写、决策、行动项、脱敏、权限和异议，返回 `PUBLISH / REVIEW / BLOCKED`。详见 [纪要发布说明](docs/ENTERPRISE_MINUTES_PUBLICATION.md)。

## 行动项履约与升级

`POST /api/enterprise/meetingai/action-tracking` 校验行动项责任人、截止日期和完成证据，统计完成率与逾期数量，并对关键依赖阻塞或逾期事项输出 `ESCALATE`，避免会议纪要发布后无人跟进。
