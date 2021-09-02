package com.etaskify.mailservice.service;

import com.etaskify.mailservice.dto.MailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author IbrahimShirinov
 * @since 02.09.2021
 */

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final KafkaMsgPublisher kafkaMsgPublisher;

    @Value("${spring.kafka.topics.assignTask}")
    private String assignTaskTopic;

    public MailDto sendWhenAssignedTask(MailDto mailDto) {
        kafkaMsgPublisher.publish(null,mailDto,assignTaskTopic);
        return mailDto;
    }
}
