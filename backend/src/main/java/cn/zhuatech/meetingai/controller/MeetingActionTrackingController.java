/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.meetingai.controller;import cn.zhuatech.meetingai.common.ApiResponse;import cn.zhuatech.meetingai.service.MeetingActionTrackingService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController@RequestMapping("/api/enterprise/meetingai")public class MeetingActionTrackingController{private final MeetingActionTrackingService service;/**
                                                                                                                                                            * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                            */
public MeetingActionTrackingController(MeetingActionTrackingService s){service=s;}/**
                                                                                                                                                                                                                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                              */
@PostMapping("/action-tracking")public ApiResponse<MeetingActionTrackingService.Result>evaluate(@Valid@RequestBody MeetingActionTrackingService.Request r){return ApiResponse.ok("会议行动项评估完成",service.evaluate(r));}}
