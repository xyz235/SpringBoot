package top.zhangdashuai.pdf.utils;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.attach.impl.OutlineHandler;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author zhangdashuai
 */
public class PdfUtil {

    /**
     * html转pdf
     */
    public static void html2Pdf(String htmlContent, String targetFile) {
        if (htmlContent == null) {
            htmlContent = "";
        }

        WriterProperties writerProperties = new WriterProperties();
        writerProperties.setFullCompressionMode(true);

        PdfWriter pdfWriter = null;
        try {
            pdfWriter = new PdfWriter(targetFile, writerProperties);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setTagged();

            PageSize pageSize = PageSize.A4;
            pdfDocument.setDefaultPageSize(pageSize);


            ConverterProperties converterProperties = new ConverterProperties();
            MediaDeviceDescription deviceDescription = new MediaDeviceDescription(MediaType.SCREEN);
            deviceDescription.setWidth(pageSize.getWidth());
            converterProperties.setMediaDeviceDescription(deviceDescription);

            FontProvider fontProvider = new DefaultFontProvider(true, false, true);

            converterProperties.setFontProvider(fontProvider);

            // 书签
            converterProperties.setOutlineHandler(OutlineHandler.createStandardHandler());

            HtmlConverter.convertToPdf(htmlContent, pdfDocument, converterProperties);
            pdfDocument.close();
        } catch (Exception ignored) {
        } finally {
            IOUtils.closeQuietly(pdfWriter);
        }
    }

    /**
     * 添加文字水印，并附加UUID
     *
     * @param srcFile  待加水印文件
     * @param destFile 加水印后输出文件
     * @param text     文本内容
     */
    public static void addWaterMark(String srcFile, String destFile, String text) {
        // 待加水印的文件
        com.itextpdf.text.pdf.PdfReader reader;
        try {
            reader = new com.itextpdf.text.pdf.PdfReader(srcFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 加完水印的文件
        PdfStamper stamper;
        try {
            stamper = new PdfStamper(reader, Files.newOutputStream(Paths.get(destFile)));
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }

        PdfContentByte content;

        // 设置透明度
        PdfGState gs = new PdfGState();
        gs.setFillOpacity(0.3f);
        // 设置字体
        BaseFont base;
        try {
            base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
        // 循环对每页插入水印
        for (int i = 1; i < reader.getNumberOfPages() + 1; i++) {
            // 水印的起始
            content = stamper.getOverContent(i);
            content.setGState(gs);
            content.setFontAndSize(base, 12);
            // 开始
            content.beginText();
            // 设置颜色 默认为黑色
            content.setColorFill(BaseColor.BLACK);
            // 开始写入水印
            content.showTextAligned(Element.ALIGN_MIDDLE, text, 0, 0, 45);
            content.endText();
        }
        try {
            stamper.close();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}