package modules.rspc;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.comm.ActionResultMap;
import platform.comm.BaseAction;
import platform.utils.HttpUtils;

import java.util.Date;
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
@RequestMapping("/rspc/event/data")
public class Event extends BaseAction {

    @Value("#{config.api_host_name}")
    private String apiHost;

    @ResponseBody
    @RequestMapping("get")
    public ActionResultMap get(String rulename,
                               @RequestParam(defaultValue = "0")int page,
                               @RequestParam(defaultValue = "15")int pageSize,
                               @DateTimeFormat(pattern = "yyyy-MM-dd")Date bDate,
                               @DateTimeFormat(pattern = "yyyy-MM-dd")Date eDate){
        Map<String ,String> param= new HashMap<>();
        if(StringUtils.isNotBlank(rulename)){
            param.put("rulename",rulename);
        }
        param.put("page_size",pageSize+"");
        param.put("page",(page+1)+"");
        param.put("token",getAdminUser().getSalt());
        HttpUtils.HttpRuest httpRuest = HttpUtils.get(apiHost.concat(Rspc.eventUrl), param);
        if(httpRuest.getStatusCode()==200){
            resultMap.setSuccess(true);
            resultMap.setData(JSON.parseObject(httpRuest.getEntity()));
        }else if(httpRuest.getStatusCode()==403){
            throw new AuthorizationException();
        } else {
            resultMap.setSuccess(false);
        }
        return  resultMap;
    }
    @ResponseBody
    @RequestMapping("statistic")
    public ActionResultMap statistic(){
        HttpUtils.HttpRuest httpRuest = HttpUtils.get(apiHost.concat(Rspc.eventStatisticUrl).concat("?token=").concat(getAdminUser().getSalt()), null);
        if(httpRuest.getStatusCode()==200){
            resultMap.setSuccess(true);
            resultMap.setData(JSON.parseObject(httpRuest.getEntity()));
        }else if(httpRuest.getStatusCode()==403){
            throw new AuthorizationException();
        } else {
            resultMap.setSuccess(false);
        }

        return  resultMap;
    }

}
