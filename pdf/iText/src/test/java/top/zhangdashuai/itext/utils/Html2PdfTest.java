package top.zhangdashuai.itext.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class Html2PdfTest {

    @Test
    public void html2Pdf() {
        Map<Object, Object> dataModelMap = new HashMap<>(16);
        dataModelMap.put("number", 1);
        String htmlContent = Template2HtmlUtil.template2Html("Html2Pdf.html", dataModelMap);
        PdfUtil.html2Pdf(htmlContent,"");
        PdfUtil.addWaterMark("","","");
    }
}
