package com.etaskify.mailservice.service.impl;

import com.etaskify.mailservice.service.KafkaMsgPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMsgPublisherImpl implements KafkaMsgPublisher {

    private final KafkaTemplate<Long, Object> kafkaTemplate;

    @Override
    public ListenableFuture<SendResult<Long, Object>> publish(Long key, Object value, String topic) {
        ListenableFuture<SendResult<Long, Object>> listenableFuture = kafkaTemplate.send(topic, key, value);
        try {
            listenableFuture.get();
        } catch (Exception e) {
            log.error("Error Sending the message and the exception is {}", e.getMessage());
            //TODO change to KafkaMessagePublisher exception :: extend serviceUnavailableException
            throw new RuntimeException();
        }
        listenableFuture.addCallback(result -> handleSuccess(key, value.toString(), result),
                ex -> log.error("Error Sending the message and the exception is {}", ex.getMessage()));
        return listenableFuture;
    }

    private void handleSuccess(Long key, Object value, SendResult<Long, Object> result) {
        log.info("Message Sent Successfully for the key : {} and the value is {} , partition is {}",
                key, value, result.getRecordMetadata().partition());
    }
}