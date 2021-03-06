package modules.rspc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.comm.BaseAction;
import platform.utils.HttpUtils;

/**
 * @Company 陕西识代运筹信息科技股份有限公司
 * @Discription 仪表盘
 * @Author houfengli
 * @CreateDate 2017/3/7 下午 01:18
 * @Version 1.0
 */
@Controller
@RequestMapping("/rspc")
public class Rspc extends BaseAction{

    @Value("#{config.api_host_name}")
    private String apiHost;

    public static String ruleUrl ="/rspc/v1/api/rules";

    public static String schemaUrl="/rspc/v1/api/schema";

    public static String eventUrl="/rspc/v1/api/events";

    public static String eventStatisticUrl="/rspc/v1/api/events/statistic";

    public static String configUrl="/rspc/v1/api/configure";

    public static String loginUrl="/com.asiainfo.rspc.web/v1/api/login?token=";

    /**
     * 仪表盘
     * @return
     */
    @RequestMapping("dashBoard")
    public String page(){
        return "rspc/dashBoard";
    }

    /**
     * 配置
     * @return
     */
    @RequestMapping("config")
    public String config(){
        return "rspc/config";
    }
    /**
     * 规则
     * @return
     */
    @RequestMapping("rule")
    public String rule(ModelMap map){
        HttpUtils.HttpRuest httpRuest = HttpUtils.get(apiHost.concat(ruleUrl).concat("?token=").concat(getAdminUser().getSalt()), null);
        if(httpRuest.getStatusCode()==HttpUtils.SUCCESS){
            JSONObject object = JSON.parseObject(httpRuest.getEntity());
            map.put("rule", object.getString("rules"));
        }else if(HttpUtils.FORBIDDEN==httpRuest.getStatusCode()){
            throw new AuthorizationException();
        }
        return "rspc/rule";
    }
    /**
     * 数据模式
     * @return
     */
    @RequestMapping("schema")
    public String dataModel(ModelMap map){
        HttpUtils.HttpRuest httpRuest  = HttpUtils.get(apiHost.concat(schemaUrl).concat("?token=").concat(getAdminUser().getSalt()), null);
        if(httpRuest.getStatusCode()==HttpUtils.SUCCESS){
            map.put("data", JSON.parseObject(httpRuest.getEntity()));
        }else if(HttpUtils.FORBIDDEN==httpRuest.getStatusCode()){
            throw new AuthorizationException();
        }
        return "rspc/schema";
    }
    /**
     * 事件
     * @return
     */
    @RequestMapping("event")
    public String event(){
        return "rspc/event";
    }
}
