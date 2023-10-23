package top.zhangdashuai.pdf.utils;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.StringWriter;

/**
 * @author zhangdashuai
 */
public class Template2HtmlUtil {

    static Configuration cfg;

    static {
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassLoaderForTemplateLoading(Template2HtmlUtil.class.getClassLoader(), "/template");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    /**
     * 模板生成html
     */
    public static String template2Html(String templateUrl, Object dataModel) {
        StringWriter out = new StringWriter();
        try {
            cfg.getTemplate(templateUrl).process(dataModel, out);
            return out.toString();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
