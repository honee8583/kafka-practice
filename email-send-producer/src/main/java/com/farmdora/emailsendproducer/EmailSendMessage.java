package com.farmdora.emailsendproducer;

import lombok.*;

@Getter  // 직렬화할 때는 getter/setter 메서드가 필요합니다.
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmailSendMessage {
    private String from; // 이메일 송신자
    private String to; // 이메일 수신자
    private String subject; // 이메일 제목
    private String body; // 이메일 본문
}
