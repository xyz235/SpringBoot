package top.zhangdashuai.pdfbox.watermark;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

/**
 * @author zhangdashuai
 */
public class PdfBoxWatermark {
    public static void main(String[] args) throws IOException {
        // 读取原始 PDF 文件
        PDDocument document = PDDocument.load(new File("/Users/xxx/Documents/CreatePdfDemo.pdf"));

        // 遍历 PDF 中的所有页面
        for (int i = 0; i < document.getNumberOfPages(); i++) {
            PDPage page = document.getPage(i);
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

            // 设置字体和字号
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 36);
            // 添加文本水印
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 100);
            contentStream.showText("Watermark");
            contentStream.endText();

            contentStream.close();
        }

        // 保存修改后的 PDF 文件
        document.save(new File("/Users/xxx/Documents/CreatePdfDemoWatermark.pdf"));
        document.close();
    }
}
