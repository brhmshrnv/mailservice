package com.etaskify.mailservice.service;

import com.etaskify.mailservice.dto.MailDto;
import freemarker.template.TemplateException;
import javax.mail.MessagingException;

import java.io.IOException;

public interface AssignedTaskNotifyEmailService {

    void sendEmail(MailDto mailDto) throws IOException, TemplateException, MessagingException;
}
