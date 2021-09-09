package com.ssuzalal.project.configurer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class MailSenderConfigurer {

    @Value("${spring.mail.host}")
    public String host;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.transport.protocol}")
    private String protocol;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String enable;

    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private String required;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties mailProp = mailSender.getJavaMailProperties();
        mailProp.put("mail.transport.protocol", protocol);
        mailProp.put("mail.smtp.auth", auth);
        mailProp.put("mail.smtp.starttls.enable", enable);
        mailProp.put("mail.smtp.starttls.required", required);
        mailProp.put("mail.smtp.port", port);

        return mailSender;
    }
}
