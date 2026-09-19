/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.meetingai.service;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.time.LocalDate;import java.util.*;
/**
 * 把会议行动项转为可跟踪的责任、截止期、证据与升级决策。
 *
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class MeetingActionTrackingService{
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public Result evaluate(Request r){List<String>blockers=new ArrayList<>(),escalations=new ArrayList<>();int overdue=0,completed=0;
  for(ActionItem a:r.items()){
   if(a.owner().isBlank())blockers.add("行动项缺少责任人: "+a.itemId());if(a.dueDate()==null)blockers.add("行动项缺少截止日期: "+a.itemId());
   if(a.completed()){completed++;if(!a.completionEvidence())blockers.add("已完成行动项缺少完成证据: "+a.itemId());}
   else if(a.dueDate()!=null&&a.dueDate().isBefore(r.asOfDate())){overdue++;escalations.add("逾期行动项升级给 "+a.owner()+": "+a.itemId());}
   if(a.critical()&&a.dependencyBlocked()&&!a.completed())escalations.add("关键行动项依赖阻塞: "+a.itemId());
  }
  Decision d=!blockers.isEmpty()?Decision.BLOCKED:!escalations.isEmpty()?Decision.ESCALATE:Decision.ON_TRACK;
  int completionRate=r.items().isEmpty()?0:(int)Math.round(completed*100.0/r.items().size());
  List<String>actions=List.of(d==Decision.BLOCKED?"补齐责任人、截止期和完成证据后再关闭会议":d==Decision.ESCALATE?"通知责任人与会议主持人，并建立新的承诺日期":"保持行动项提醒并在下次会议复核");
  return new Result(d,completionRate,overdue,List.copyOf(blockers),List.copyOf(escalations),actions);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Request(@NotBlank String meetingId,@NotNull LocalDate asOfDate,@NotEmpty List<@Valid ActionItem>items){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record ActionItem(@NotBlank String itemId,String owner,LocalDate dueDate,boolean critical,boolean dependencyBlocked,boolean completed,boolean completionEvidence){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Result(Decision decision,int completionRatePercent,int overdueCount,List<String>blockers,List<String>escalations,List<String>actions){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public enum Decision{ON_TRACK,ESCALATE,BLOCKED}
}
