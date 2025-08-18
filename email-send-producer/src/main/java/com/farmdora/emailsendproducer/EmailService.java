package com.farmdora.emailsendproducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final KafkaTemplate<String, String > kafkaTemplate;

    public void sendEmail(SendEmailRequestDto sendEmailRequestDto) {
        EmailSendMessage emailSendMessage = EmailSendMessage.builder()
                .from(sendEmailRequestDto.getFrom())
                .to(sendEmailRequestDto.getTo())
                .subject(sendEmailRequestDto.getSubject())
                .body(sendEmailRequestDto.getBody())
                .build();

        this.kafkaTemplate.send("email.send", toJsonString(emailSendMessage));
    }

    private String toJsonString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch(JsonProcessingException e) {
            throw new RuntimeException("Json 직렬화 실패");
        }
    }
}
