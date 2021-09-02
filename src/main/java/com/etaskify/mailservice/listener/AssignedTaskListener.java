package com.etaskify.mailservice.listener;

import com.etaskify.mailservice.dto.MailDto;
import com.etaskify.mailservice.service.AssignedTaskNotifyEmailService;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class AssignedTaskListener {

    private final AssignedTaskNotifyEmailService assignedTaskNotifyEmailService;

    @KafkaListener(id = "1",
            topics = "user-assign-task-topic",
            groupId = "notification-group-id",
            containerFactory = "kafkaJsonListenerContainerFactory")
    public void assignedTaskListener(MailDto dto) throws MessagingException, IOException, TemplateException {
        log.info("Message received : {}", dto);
        assignedTaskNotifyEmailService.sendEmail(dto);
    }
}
