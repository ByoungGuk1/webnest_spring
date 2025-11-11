package com.app.webnest.domain.vo;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class GameJoinVO {
    private Long id;
    private Long userId;
    private Long gameRoomId; // 이알디 확인
    private Integer gameJoinIsHost;
    private LocalDateTime gameJoinCreateAt;
}
