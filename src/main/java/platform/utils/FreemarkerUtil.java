package platform.utils;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

public class FreemarkerUtil {
    private static final Logger log = Logger.getLogger(FreemarkerUtil.class);

    public static final String CHARSET = "UTF-8";

    public static Template getTemplate(String templateName) {
        try {
            Configuration configuration = new Configuration();
            File file = new File(Thread.currentThread().getContextClassLoader().getResources("").nextElement().getFile());
            FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(file);
            configuration.setTemplateLoader(fileTemplateLoader);
            configuration.setObjectWrapper(new DefaultObjectWrapper());
            configuration.setDefaultEncoding(CHARSET);
            return configuration.getTemplate(templateName);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
    public static Template getTemplate(File templateFile) {
        try {
            Configuration configuration = new Configuration();
            FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(templateFile.getParentFile());
            configuration.setTemplateLoader(fileTemplateLoader);
            configuration.setObjectWrapper(new DefaultObjectWrapper());
            configuration.setDefaultEncoding(CHARSET);
            return configuration.getTemplate(templateFile.getName());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
    public static void outputProcessResult(String outputFile, Template template, Map<String, Object> varMap) {
        String resultString;
        ByteArrayOutputStream baos = null;
        Writer writer = null;
        try {
            baos = new ByteArrayOutputStream();
            writer = new OutputStreamWriter(baos, CHARSET);
            template.process(varMap, writer);
            resultString = new String(baos.toByteArray(), CHARSET);
            FileUtils.write(new File(outputFile), resultString,CHARSET);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            if (null != baos) {
                try {
                    baos.close();
                } catch (Exception e) {
                    log.warn(e.getMessage(), e);
                }
            }

            if (null != writer) {
                try {
                    writer.close();
                } catch (Exception e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    }
}
