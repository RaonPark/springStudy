package com.ssuzalal.project.service.impl;

import com.ssuzalal.project.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromAddress;

    public void sendAuthMail(String to, String code) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject(Constants.AUTHORIZE_TITLE);

        String html = "";
        html += "<h1>슈잘알에서 보내는 인증코드입니다.</h1>";
        html += "<div><span>";
        html += code;
        html += "</span></div>";

        message.setText(html, "utf-8", "html");
        message.setFrom(fromAddress);

        mailSender.send(message);

        log.info("send authentication mail to " + to);
    }

    public void sendChangePasswordMail(String to) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject(Constants.CHANGE_PASSWORD_TITLE);

        String html = "<html>";
        html += "<h1>슈잘알에서 패스워드 변경을 요청했습니다.</h1><br>";
        html += "<h2>만약 본인이 아니라면 관리자에게 문의해주시기 바랍니다.</h2><br>";
        html += "<div><a href=";
        html += Constants.CHANGE_PASSWORD_URL;
        html += ">패스워드 변경하기</a></div>";
        html += "</html>";

        message.setText(html, "utf-8", "html");
        message.setFrom(fromAddress);

        mailSender.send(message);

        log.info("send change password mail to " + to);
    }

}
