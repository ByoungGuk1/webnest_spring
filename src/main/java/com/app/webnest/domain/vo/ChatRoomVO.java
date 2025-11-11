package com.app.webnest.domain.vo;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class ChatRoomVO {
    private Long id;
    private Long gameRoomId;
    private Long userId;
}
