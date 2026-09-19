/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.meetingai.config;
import cn.zhuatech.meetingai.model.*; import cn.zhuatech.meetingai.repository.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import org.springframework.security.crypto.password.PasswordEncoder; import java.time.LocalDate; import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Configuration public class DataInitializer {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Bean CommandLineRunner seed(OperatingUnitRepository units,WorkRecordRepository orders,ResourceRegisterRepository resources,ReviewRecordRepository reviews,UserRepository users,PasswordEncoder encoder){return args->{if(units.count()>0)return;
 var u1=units.save(new OperatingUnit("MEET-OPS","会议运营组","总裁办",180));var u2=units.save(new OperatingUnit("MEET-PROD","产品例会组","产品中心",120));var u3=units.save(new OperatingUnit("MEET-CS","客户协同组","交付中心",96));
 var t1=orders.save(new WorkRecord("MTG-260804-018","Q3-BUSINESS-REVIEW","季度经营复盘会",u1,24,16,1,LocalDate.now().plusDays(1),WorkRecord.Status.RUNNING,"经营复盘"));
 var t2=orders.save(new WorkRecord("MTG-260804-021","PRODUCT-ROADMAP","产品路线图评审",u2,18,8,0,LocalDate.now().plusDays(2),WorkRecord.Status.RUNNING,"决策会"));
 var t3=orders.save(new WorkRecord("MTG-260804-026","KEY-ACCOUNT-DELIVERY","重点客户交付协调会",u1,12,0,0,LocalDate.now().plusDays(3),WorkRecord.Status.RELEASED,"协调会"));
 var t4=orders.save(new WorkRecord("MTG-260803-015","RND-WEEKLY","研发周例会",u3,20,20,1,LocalDate.now(),WorkRecord.Status.COMPLETED,"周例会"));
 resources.saveAll(List.of(new ResourceRegister("STT-PIPE-01","实时转写流水线",u1,ResourceRegister.Status.RUNNING,96),new ResourceRegister("SUMMARY-02","纪要生成引擎",u2,ResourceRegister.Status.IDLE,88),new ResourceRegister("ACTION-03","行动项提醒服务",u3,ResourceRegister.Status.RUNNING,91),new ResourceRegister("CALENDAR-04","会议日历连接器",u1,ResourceRegister.Status.ALARM,72)));
 reviews.saveAll(List.of(new ReviewRecord("RV-260804-032",t1,"人工复核",6,0,ReviewRecord.Result.PASSED,"沈知行"),new ReviewRecord("RV-260804-011",t2,"质量检查",3,0,ReviewRecord.Result.PASSED,"周言"),new ReviewRecord("RV-260803-018",t4,"结果抽查",5,1,ReviewRecord.Result.FAILED,"沈知行"),new ReviewRecord("RV-260804-003",t3,"上线确认",4,0,ReviewRecord.Result.PENDING,"周言")));
 String demo=encoder.encode("Demo@2026");users.saveAll(List.of(new UserAccount("operator",demo,"周言",UserAccount.Role.DOMAIN_USER,"MEET-OPS"),new UserAccount("planner",demo,"沈知行",UserAccount.Role.DOMAIN_OPERATOR,null),new UserAccount("quality",demo,"顾清",UserAccount.Role.QUALITY,null),new UserAccount("admin",encoder.encode("ZhuaTech@2026"),"系统管理员",UserAccount.Role.ADMIN,null)));};}
}