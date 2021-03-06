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
        HttpUtils.HttpRuest httpRuest = HttpUtils.get(apiHost.concat(Rspc.schemaUrl).concat("?token=").concat(getAdminUser().getSalt()), null);
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
        Map map = new HashMap();
        map.put("schema",body);
        HttpUtils.HttpRuest put = HttpUtils.put(apiHost.concat(Rspc.schemaUrl).concat("?token=").concat(getAdminUser().getSalt()), JSON.toJSONString(map));
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
    @ResponseBody
    @RequestMapping("save")
    public ActionResultMap save(String body){
        Map map = new HashMap();
        map.put("schema",body);
        HttpUtils.HttpRuest post = HttpUtils.post(apiHost.concat(Rspc.schemaUrl).concat("?token=").concat(getAdminUser().getSalt()), JSON.toJSONString(map));
        if(post.getStatusCode()==HttpUtils.CREATED){
            resultMap.setSuccess(true);
            resultMap.setData(JSON.parseObject(post.getEntity()));
        }else {
            resultMap.setSuccess(false);
        }
        return  resultMap;
    }
    @ResponseBody
    @RequestMapping("dele")
    public ActionResultMap dele(){
        HttpUtils.HttpRuest delte = HttpUtils.delte(apiHost.concat(Rspc.schemaUrl).concat("?token=").concat(getAdminUser().getSalt()), null);

        if(delte.getStatusCode()==HttpUtils.NO_CONTENT){
            resultMap.setSuccess(true);
            resultMap.setData(JSON.parseObject(delte.getEntity()));
        }else if(delte.getStatusCode()==HttpUtils.FORBIDDEN){
            throw new AuthorizationException();
        } else {
            resultMap.setSuccess(false);
        }
        return  resultMap;
    }
}
