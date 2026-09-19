/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.meetingai;import cn.zhuatech.meetingai.service.DecisionCompletenessService;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class DecisionCompletenessServiceTests{private final DecisionCompletenessService s=new DecisionCompletenessService();/**
                                                                                                                      * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                      */
@Test void distributesCompleteDecisions(){var r=s.evaluate(new DecisionCompletenessService.Request(5,5,5,0,100,true));assertEquals("DISTRIBUTE",r.status());}/**
                                                                                                                                                                                                                                                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                   */
@Test void rejectsOwnerlessNotes(){var r=s.evaluate(new DecisionCompletenessService.Request(5,0,0,5,30,false));assertEquals("INCOMPLETE",r.status());}}
