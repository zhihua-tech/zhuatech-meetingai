/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.meetingai.workbench;
import org.springframework.stereotype.Service;
import java.math.*;
import java.util.*;
import static cn.zhuatech.meetingai.workbench.WorkbenchInput.*;
/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@Service
public class AiWorkbenchService {
    /** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    public Map<String,Object> execute(Map<String,Object> r){
        validate(r);

        keys(r,"actions");var items=rows(r,"actions",1,200);Map<String,Map<String,Object>> grouped=new LinkedHashMap<>();Set<String> ids=new HashSet<>();int conflicts=0;
        for(var item:items){keys(item,"id","task","owner","dueDate");String id=id(item,"id"),task=text(item,"task",2000).trim(),owner=text(item,"owner",120).trim(),date=text(item,"dueDate",10);try{java.time.LocalDate.parse(date);}catch(java.time.DateTimeException e){throw new IllegalArgumentException("dueDate须为有效ISO日期");}if(!ids.add(id))throw new IllegalArgumentException("行动项编号重复");
            String normalized=java.text.Normalizer.normalize(task,java.text.Normalizer.Form.NFKC).replaceAll("\\s+"," ").toLowerCase(Locale.ROOT);String key=owner.toLowerCase(Locale.ROOT)+"\u0000"+normalized;
            var existing=grouped.get(key);if(existing==null){var value=new LinkedHashMap<String,Object>();value.put("task",task);value.put("owner",owner);value.put("dueDate",date);value.put("sourceIds",new ArrayList<>(List.of(id)));value.put("dateConflict",false);grouped.put(key,value);}
            else{ @SuppressWarnings("unchecked")var sources=(List<String>)existing.get("sourceIds");sources.add(id);String previous=(String)existing.get("dueDate");if(!previous.equals(date)){if(!Boolean.TRUE.equals(existing.get("dateConflict")))conflicts++;existing.put("dateConflict",true);}if(date.compareTo(previous)<0)existing.put("dueDate",date);}
        }
        return Map.of("actions",List.copyOf(grouped.values()),"mergedDuplicates",items.size()-grouped.size(),"dateConflicts",conflicts,"createsTasks",false);

    }
}
