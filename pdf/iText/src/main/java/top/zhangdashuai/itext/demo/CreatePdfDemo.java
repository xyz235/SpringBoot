package top.zhangdashuai.itext.demo;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

/**
 * @author zhangdashuai
 */
public class CreatePdfDemo {

    private static final String FILE_PATH = "/Users/xxx/Documents/CreatePdfDemo.pdf";

    private static final String CHINESS_FONT = "simhei.ttf";


    public static void main(String[] args) throws Exception {
        //【1】创建Document对象
        Document document = new Document(PageSize.A4);
        //【2】创建Writer实例
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FILE_PATH));
        //【3】打开文档
        document.open();
        //【4】写入内容
        document.add(new Paragraph("hello world 张大帅", FontFactory.getFont(CHINESS_FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED)));
        //【5】关闭文档
        document.close();
        pdfWriter.close();
    }
}
