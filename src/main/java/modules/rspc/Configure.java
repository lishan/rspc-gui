package modules.rspc;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.AuthorizationException;
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
        Map<String,String> parma = new HashMap<String,String>();
        parma.put("token",getAdminUser().getSalt());
        HttpUtils.HttpRuest httpRuest = HttpUtils.get(apiHost.concat(Rspc.configUrl), parma);
        if(httpRuest.getStatusCode()==HttpUtils.SUCCESS){
            resultMap.setSuccess(true);
            resultMap.setData(JSON.parseObject(httpRuest.getEntity()));
        }else if(httpRuest.getStatusCode()==HttpUtils.FORBIDDEN){
            throw new AuthorizationException();
        } else {
            resultMap.setSuccess(false);
        }

        return  resultMap;
    }

    @ResponseBody
    @RequestMapping("update")
    public ActionResultMap update(String body){
        HttpUtils.HttpRuest put = HttpUtils.put(apiHost.concat(Rspc.configUrl).concat("?token=").concat(getAdminUser().getSalt()), body);
        if(put.getStatusCode()==HttpUtils.SUCCESS){
            resultMap.setSuccess(true);
            resultMap.setData(JSON.parseObject(put.getEntity()));
        }else if(put.getStatusCode()==HttpUtils.FORBIDDEN){
            throw new AuthorizationException();
        } else {
            resultMap.setSuccess(false);
        }
        return  resultMap;
    }

    @RequestMapping("/index")
    public String index(){
        return "config";
    }


}
