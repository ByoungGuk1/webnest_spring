package com.app.webnest.domain.vo;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class ChatMessageVO {
    private Long id;
    private String chatMessageContent;
    private LocalDateTime chatMessageCreateAt;
    private Long userSenderId;
    private Long userReceiverId;
    private Long gameRoomId;
}