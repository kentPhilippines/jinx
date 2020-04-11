package com.ruoyi.framework.mail;

import com.ruoyi.common.core.domain.AjaxResult;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


public class GmailManager {

    private final String username = "kiwi20200315@gmail.com";

    private final String password = "Kiwi@20200315";

    private final String host = "smtp.gmail.com";

    private final String sslPort = "465";

    private final String tlsPort = "587";

    private final String sslEnable = "true";

    private final String tlsEnable = "true";

    private final String auth = "true";

    private final String factory = "javax.net.ssl.SSLSocketFactory";

    private void sslConnect(Properties props) {
        props.put("mail.debug", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", sslPort);
        props.put("mail.smtp.ssl.enable", sslEnable);
        props.put("mail.smtp.socketFactory.class", factory);
        props.put("mail.smtp.socketFactory.port", sslPort);
        props.put("mail.smtp.auth", auth);
    }

    private void tlsConnect(Properties props) {
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", tlsEnable);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", tlsPort);
    }

    /**
     * 发送邮件
     *
     * @param email   收件地址
     * @param subject 邮件主题
     * @param text    邮件内容
     * @return 返回结果
     */
    public AjaxResult emailSender(String email, String subject, String text) {
        Properties props = new Properties();
        //选择ssl方式
        sslConnect(props);
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject("测试邮件2");
            msg.setText("这是一个测试邮件，请不要回复");
            msg.setSentDate(new Date());
            Transport.send(msg);
        } catch (AddressException e) {
            return AjaxResult.error("无效的收件地址");
        } catch (MessagingException e) {
            e.printStackTrace();
            return AjaxResult.error("信息错误");
        }
        return AjaxResult.success();
    }

}
