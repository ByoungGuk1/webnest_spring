package com.app.webnest.domain.vo;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class ChatMessageVO {
    private Long id;
    private Long chatRoomId;
    private Long userId;
    private String chatMessageContent;
}
