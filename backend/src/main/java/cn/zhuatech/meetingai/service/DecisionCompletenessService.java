/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.meetingai.service;import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class DecisionCompletenessService{/**
                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                   */
public Result evaluate(Request r){double owner=r.decisions()==0?100:r.decisionsWithOwner()*100.0/r.decisions();double due=r.decisions()==0?100:r.decisionsWithDueDate()*100.0/r.decisions();int score=(int)Math.round(owner*.35+due*.25+r.transcriptCoverage()*.2+(r.approvalsCaptured()?20:0)-Math.min(20,r.unresolvedTopics()*4));score=Math.max(0,Math.min(100,score));List<String> gaps=new ArrayList<>();if(owner<100)gaps.add("为所有决策指定负责人");if(due<100)gaps.add("补充行动截止日期");if(r.unresolvedTopics()>0)gaps.add("记录未决议题和后续讨论安排");if(!r.approvalsCaptured())gaps.add("确认关键决策的批准人");String status=score>=90?"DISTRIBUTE":score>=60?"REVIEW":"INCOMPLETE";if(gaps.isEmpty())gaps.add("会议决策和行动项可直接分发");return new Result(score,status,gaps);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Request(@Min(0) int decisions,@Min(0) int decisionsWithOwner,@Min(0) int decisionsWithDueDate,@Min(0) int unresolvedTopics,@DecimalMin("0") @DecimalMax("100") double transcriptCoverage,@NotNull Boolean approvalsCaptured){}/**
                                                                                                                                                                                                                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                              */
public record Result(int completenessScore,String status,List<String> gaps){} }
