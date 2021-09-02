package com.etaskify.mailservice.service;

import javax.mail.MessagingException;

public interface SendMailService {
    void send(String sendTo, String subject, String htmlBody) throws MessagingException;
}
