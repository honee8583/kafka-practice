package com.farmdora.emailsendconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailSendDltConsumer {

    @KafkaListener(
            topics = "email.send.dlt",
            groupId = "email-send-dlt-group"
    )
    public void consume(String message) {
        // 로그 시스템에 전송
        log.info("로그 시스템에 전송: {}", message);

        // 알림 발송
        log.info("Slack으로 알림 발송: {}", message);
    }
}
