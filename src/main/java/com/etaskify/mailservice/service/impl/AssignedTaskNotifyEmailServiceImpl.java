package com.etaskify.mailservice.service.impl;

import com.etaskify.mailservice.dto.MailDto;
import com.etaskify.mailservice.service.AssignedTaskNotifyEmailService;
import com.etaskify.mailservice.service.SendMailService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Service
public class AssignedTaskNotifyEmailServiceImpl implements AssignedTaskNotifyEmailService {

    private static final String SUBJECT = "Notification for assigned task";

    private final FreeMarkerConfigurer freemarkerConfigurer;
    private final SendMailService sendMailService;

    @Value("${spring.mail.username}")
    private String hostMail;

    @Override
    public void sendEmail(MailDto dto) throws IOException, TemplateException, MessagingException {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("recipientName", dto.getEmail());
        templateModel.put("senderName", hostMail);
        templateModel.put("task",dto.getTaskTitle());
        Template template = freemarkerConfigurer.getConfiguration().getTemplate("assigned-task.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateModel);
        sendMailService.send(dto.getEmail(), SUBJECT, htmlBody);
        log.info("Email for assigned task sent to email {}", dto.getEmail());
    }
}
