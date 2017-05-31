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

/**
 * @Company 陕西识代运筹信息科技股份有限公司
 * @Discription
 * @Author houfengli
 * @CreateDate 2017/3/14 下午 04:50
 * @Version 1.0
 */
@Controller
@RequestMapping("/rspc/config/data")
public class Configure extends BaseAction{

    @Value("#{config.api_host_name}")
    private String apiHost;

    @ResponseBody
    @RequestMapping("get")
    public ActionResultMap get(){
        String s = HttpUtils.get(apiHost.concat(Rspc.configUrl), null);
        if(StringUtils.isNotBlank(s)){
            resultMap.setSuccess(true);
            resultMap.setData(JSON.parseObject(s));
        }else {
            resultMap.setSuccess(false);
        }

        return  resultMap;
    }

    @ResponseBody
    @RequestMapping("update")
    public ActionResultMap update(String body){
        String s = HttpUtils.put(apiHost.concat(Rspc.configUrl), body);
        if(StringUtils.isNotBlank(s)){
            resultMap.setSuccess(true);
            resultMap.setData(JSON.parseObject(s));
        }else {
            resultMap.setSuccess(false);
        }
        return  resultMap;
    }

    @RequestMapping("/index")
    public String index(){
        return "config";
    }


}
