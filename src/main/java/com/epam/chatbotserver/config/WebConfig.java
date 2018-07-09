package com.epam.chatbotserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan("com.epam.chatbotserver")
@PropertySource("classpath:mail/email.properties")
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private Environment env;

    @Bean
    public JavaMailSenderImpl javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("email.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("email.port")));
        mailSender.setUsername(env.getProperty("email.username"));
        mailSender.setPassword(env.getProperty("email.password"));
        Properties properties = mailSender.getJavaMailProperties();
        properties.put(env.getProperty("email.protocol"), env.getProperty("email.protocol.val"));
        properties.put(env.getProperty("email.auth"), env.getProperty("email.auth.val"));
        properties.put(env.getProperty("email.starttls"), env.getProperty("email.starttls.val"));
        return mailSender;
    }
}
