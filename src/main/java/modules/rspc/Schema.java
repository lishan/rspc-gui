package modules.rspc;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.comm.ActionResultMap;
import platform.comm.BaseAction;
import platform.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Company 陕西识代运筹信息科技股份有限公司
 * @Discription
 * @Author houfengli
 * @CreateDate 2017/3/15 下午 04:26
 * @Version 1.0
 */
@Controller
@RequestMapping("/rspc/schema/data")
public class Schema  extends BaseAction {

    @Value("#{config.api_host_name}")
    private String apiHost;

    @ResponseBody
    @RequestMapping("get")
    public ActionResultMap get(){
        String s = HttpUtils.get(apiHost.concat(Rspc.schemaUrl), null);
        resultMap.setSuccess(true);
        if(StringUtils.isNotBlank(s)){
            resultMap.setData(JSON.parseObject(s));
        }
        return  resultMap;
    }
    @ResponseBody
    @RequestMapping("update")
    public ActionResultMap update(String body){
        Map map = new HashMap();
        map.put("schema",body);
        String s = HttpUtils.put(apiHost.concat(Rspc.schemaUrl), JSON.toJSONString(map));
        resultMap.setSuccess(true);
        resultMap.setData(s);
        return  resultMap;
    }
    @ResponseBody
    @RequestMapping("save")
    public ActionResultMap save(String body){
        Map map = new HashMap();
        map.put("schema",body);
        String s = HttpUtils.post(apiHost.concat(Rspc.schemaUrl), JSON.toJSONString(map));
        resultMap.setSuccess(true);
        resultMap.setData(s);
        return  resultMap;
    }
    @ResponseBody
    @RequestMapping("dele")
    public ActionResultMap dele(){
        String s = HttpUtils.delte(apiHost.concat(Rspc.schemaUrl), null);
        resultMap.setSuccess(true);
        resultMap.setData(JSON.parseObject(s));
        return  resultMap;
    }
}
