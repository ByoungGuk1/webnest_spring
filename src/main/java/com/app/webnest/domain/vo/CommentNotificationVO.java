package com.app.webnest.domain.vo;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class CommentNotificationVO {
    private Long id;
    private Long commentId;
    private Long userId;
    private String commentNotificationAction;
    private boolean commentNotificationIsRead;
}