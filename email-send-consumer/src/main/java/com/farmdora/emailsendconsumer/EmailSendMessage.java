package com.farmdora.emailsendconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor  // 역직렬화에는 빈 생성자가 필요합니다.
@AllArgsConstructor
public class EmailSendMessage {
    private String from; // 이메일 송신자
    private String to; // 이메일 수신자
    private String subject; // 이메일 제목
    private String body; // 이메일 본문

    public static EmailSendMessage fromJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, EmailSendMessage.class);
        } catch(JsonProcessingException e) {
            throw new RuntimeException("Json 파싱 실패");
        }
    }
}
