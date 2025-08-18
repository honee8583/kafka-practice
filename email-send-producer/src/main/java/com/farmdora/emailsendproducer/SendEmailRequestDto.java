package com.farmdora.emailsendproducer;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailRequestDto {
    private String from; // 이메일 송신자
    private String to; // 이메일 수신자
    private String subject; // 이메일 제목
    private String body; // 이메일 본문
}
