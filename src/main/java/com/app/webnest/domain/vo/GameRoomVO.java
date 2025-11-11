package com.app.webnest.domain.vo;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class GameRoomVO {
    private Long id; // erd pk키 확인
    private String gameRoomTitle;
    private boolean gameRoomIsTeam;
    private String gameRoomType; // erd 게임 유형 중복
    private Integer gameRoomMaxPlayer;
    private boolean gameRoomIsStart;
    private String gameRoomPassKey;
}
