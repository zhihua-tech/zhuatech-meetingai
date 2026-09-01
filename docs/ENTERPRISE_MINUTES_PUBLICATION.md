# 企业级会议纪要发布治理

`POST /api/enterprise/meetingai/minutes-publication` 检查参会告知、保密分级、转写覆盖、决策、行动项、脱敏、权限、异议、保留和变更审计，返回 `PUBLISH / REVIEW / BLOCKED`。

生产系统应对敏感会议限制转写和模型调用范围，纪要修改后必须保留原版本并按规则重新批准。
