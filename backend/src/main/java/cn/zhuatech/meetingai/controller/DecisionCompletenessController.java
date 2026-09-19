/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.meetingai.controller;import cn.zhuatech.meetingai.common.ApiResponse;import cn.zhuatech.meetingai.service.DecisionCompletenessService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/meetingai/insights/decision-completeness") public class DecisionCompletenessController{private final DecisionCompletenessService service;/**
                                                                                                                                                                                * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                */
public DecisionCompletenessController(DecisionCompletenessService service){this.service=service;}/**
                                                                                                                                                                                                                                                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                 */
@PostMapping ApiResponse<DecisionCompletenessService.Result> evaluate(@Valid @RequestBody DecisionCompletenessService.Request r){return ApiResponse.ok(service.evaluate(r));}}
