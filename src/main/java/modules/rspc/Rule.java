package modules.rspc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.comm.ActionResultMap;
import platform.comm.BaseAction;
import platform.utils.HttpUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
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
@RequestMapping("/rspc/rule/data")
public class Rule extends BaseAction{

    @Value("#{config.api_host_name}")
    private String apiHost;

    @ResponseBody
    @RequestMapping("get")
    public ActionResultMap get(){
        Map map = new HashMap();
        map.put("token",getAdminUser().getSalt());
        HttpUtils.HttpRuest httpRuest = HttpUtils.get(apiHost.concat(Rspc.ruleUrl), map);
        if(httpRuest.getStatusCode()==200){
            JSONObject object = JSON.parseObject(httpRuest.getEntity());
            resultMap.setSuccess(true);
            resultMap.setData(object.getString("rules"));
        }else if(httpRuest.getStatusCode()==403){
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
        map.put("rules",body);
        HttpUtils.HttpRuest put = HttpUtils.put(apiHost.concat(Rspc.ruleUrl).concat("?token=").concat(getAdminUser().getSalt()), JSON.toJSONString(map));
        if(put.getStatusCode()==200){
            JSONObject object = JSON.parseObject(put.getEntity());
            resultMap.setSuccess(true);
            resultMap.setData(object.getString("rules"));
        }else if(put.getStatusCode()==403){
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
        map.put("rules",body);
        HttpUtils.HttpRuest post = HttpUtils.post(apiHost.concat(Rspc.ruleUrl).concat("?token=").concat(getAdminUser().getSalt()), JSON.toJSONString(map));
        if(post.getStatusCode()==201){
            JSONObject object = JSON.parseObject(post.getEntity());
            resultMap.setSuccess(true);
            resultMap.setData(object.getString("rules"));
        }else if(post.getStatusCode()==403){
            throw new AuthorizationException();
        } else {
            resultMap.setSuccess(false);
        }
        return  resultMap;
    }
    @ResponseBody
    @RequestMapping("dele")
    public ActionResultMap dele(){
        HttpUtils.HttpRuest delte = HttpUtils.delte(apiHost.concat(Rspc.ruleUrl), null);
        if(delte.getStatusCode()==204){
            resultMap.setSuccess(true);
            resultMap.setData(JSON.parseObject(delte.getEntity()));
        }else if(delte.getStatusCode()==403){
            throw new AuthorizationException();
        } else {
            resultMap.setSuccess(false);
        }

        return  resultMap;
    }

    /**
     * 读取文件
     * @param request
     * @param response
     * @param fileName
     * @return
     */
    @RequestMapping("ajax/read")
    @ResponseBody
    public String read(HttpServletRequest request,HttpServletResponse response,String fileName){
        String path=this.getClass().getClassLoader().getResource("").getPath();
        File file=new File(path.replace("/WEB-INF/classes/","")+"/upload/",fileName);
        StringBuffer texts=new StringBuffer();
        try {
            String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
            if("txt".equals(prefix)){
                FileReader fr=new FileReader(file);
                BufferedReader br=new BufferedReader(fr);
                String text="";
                while ((text=br.readLine())!=null){
                    texts.append(text);
                    texts.append("/n");
                }
                br.close();
                fr.close();
            }
            if("doc".equals(prefix)){
                FileInputStream fileInputStream=new FileInputStream(file);
                WordExtractor wordExtractor=new WordExtractor(fileInputStream);
                texts.append(wordExtractor.getText());
                fileInputStream.close();
            }

            if ("docx".equals(prefix)){
                OPCPackage opcPackage = POIXMLDocument.openPackage(file.getPath());
                POIXMLTextExtractor extractor = null;
                try {
                    extractor = new XWPFWordExtractor(opcPackage);
                    texts.append(extractor.getText());
                    opcPackage.close();
                } catch (XmlException e) {
                    e.printStackTrace();
                } catch (OpenXML4JException e) {
                    e.printStackTrace();
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return texts.toString();
    }
}
