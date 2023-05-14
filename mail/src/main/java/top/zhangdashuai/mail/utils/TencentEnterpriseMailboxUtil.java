package top.zhangdashuai.mail.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

/**
 * POP3/SMTP协议
 * 接收邮件服务器：pop.exmail.qq.com ，使用SSL，端口号995
 * 发送邮件服务器：smtp.exmail.qq.com ，使用SSL，端口号465
 * 海外用户可使用以下服务器
 * 接收邮件服务器：hwpop.exmail.qq.com ，使用SSL，端口号995
 * 发送邮件服务器：hwsmtp.exmail.qq.com ，使用SSL，端口号465
 * --------------------------------------------------
 * IMAP协议
 * 接收邮件服务器：imap.exmail.qq.com  ，使用SSL，端口号993
 * 发送邮件服务器：smtp.exmail.qq.com ，使用SSL，端口号465
 * 海外用户可使用以下服务器
 * 接收邮件服务器：hwimap.exmail.qq.com ，使用SSL，端口号993
 * 发送邮件服务器：hwsmtp.exmail.qq.com ，使用SSL，端口号465
 *
 * @author zhangdashuai
 */
public class TencentEnterpriseMailboxUtil {
    public static String receiveMailAccount = "xxx@xxx.xxx";
    public static String fileSrc = "xxx";
    public static String subject = "xxx";
    public static String text = "xxx";

    public static void main(String[] args) {
        sendEmail(receiveMailAccount, fileSrc, subject, text);
    }

    /**
     * 登陆账号
     */
    private static final String LOGIN_ACCOUNT = "xxx@xxx.xxx";

    /**
     * 登录密码
     */
    private static final String LOGIN_PASSWORD = "xxx";

    /**
     * 服务器地址
     */
    private static final String MAIL_SMTP_HOST = "smtp.exmail.qq.com";

    /**
     * 端口号
     */
    private static final String MAIL_SMTP_PORT = "465";

    /**
     * 协议
     */
    private static final String MAIL_TRANSPORT_PROTOCOL = "smtp";
    public static String companyPersonal = "xxx";
    public static String charset = "UTF-8";

    public static void sendEmail(String receiveMailAccount, String fileSrc, String subject, String text) {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);
        props.setProperty("mail.smtp.host", MAIL_SMTP_HOST);
        props.setProperty("mail.smtp.port", MAIL_SMTP_PORT);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // 使用smtp身份验证
        props.setProperty("mail.smtp.auth", "true");

        // 使用SSL，企业邮箱必需！
        // 开启安全协议
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        // 获取Session对象
        Session s = Session.getDefaultInstance(props, new Authenticator() {
            // 此访求返回用户和密码的对象
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(LOGIN_ACCOUNT, LOGIN_PASSWORD);
            }
        });
        // 设置session的调试模式，发布时取消
        s.setDebug(false);
        MimeMessage mimeMessage = new MimeMessage(s);
        try {
            mimeMessage.setFrom(new InternetAddress(LOGIN_ACCOUNT, companyPersonal, charset));
            mimeMessage.addRecipients(Message.RecipientType.TO, receiveMailAccount);
            // 设置主题
            mimeMessage.setSubject(subject, charset);
            // 时间
            mimeMessage.setSentDate(new Date());
            if (!Objects.equals(fileSrc, "")) {
                // 容器类 附件
                MimeMultipart mimeMultipart = new MimeMultipart();
                // 可以包装文本,图片,附件
                MimeBodyPart bodyPart = new MimeBodyPart();
                // 设置内容
                bodyPart.setContent(text, "text/html; charset=UTF-8");
                mimeMultipart.addBodyPart(bodyPart);
                // 添加图片&附件
                bodyPart = new MimeBodyPart();
                bodyPart.attachFile(fileSrc);
                mimeMultipart.addBodyPart(bodyPart);
                mimeMessage.setContent(mimeMultipart);
            } else {
                // 设置内容
                mimeMessage.setText(text);
            }
            // 保存设置
            mimeMessage.saveChanges();
            // 发送
            Transport.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
