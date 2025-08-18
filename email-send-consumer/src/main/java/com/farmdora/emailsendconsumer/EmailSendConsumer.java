package com.farmdora.emailsendconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class EmailSendConsumer {

    // groupId에 지정한 consumer group이 kafka에 존재하지 않는다면 생성해줍니다.
    // 존재한다면 다음으로 읽어야할 메시지를 읽어옵니다. 이후에 실시간으로 저장되는 메시지를 읽어옵니다.
    // consumer group을 사용하기 때문에 메시지를 어디까지 읽었는지에 대한 offset값을 같이 저장해줍니다.
    @KafkaListener(
            topics = "email.send",
            groupId = "email-send-group"
    )
    // 현업에서는 보통 재시도 횟수는 3~5 사이로 정합니다.
    // 재시도를 너무 많이 할 경우 시스템 부하가 커질 수 있습니다.
    @RetryableTopic(
            attempts = "5", // 총 시도횟수
            backoff = @Backoff(delay = 1000, multiplier = 2), // 재시도 간격 조정(delay: 기본 딜레이 간격, multiplier: 다음 재시도의 딜레이를 그만큼 곱합니다)
            dltTopicSuffix = ".dlt" // 지정하지 않을 경우 -dlt 형식으로 생깁니다.
    )
    public void consume(String message) { // 읽은 메시지는 파라미터로 들어옵니다.
        System.out.println("Kafka로부터 받아온 메시지: " + message);

        EmailSendMessage emailSendMessage = EmailSendMessage.fromJson(message);

        if(emailSendMessage.getTo().equals("fail@naver.com")) {
            String errorMessage = "잘못된 이메일 주소로 인해 발송 실패";
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        // 이메일 발송 로직
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            throw new RuntimeException("이메일 발송 실패");
        }

        System.out.println("이메일 발송 완료");
    }
}
