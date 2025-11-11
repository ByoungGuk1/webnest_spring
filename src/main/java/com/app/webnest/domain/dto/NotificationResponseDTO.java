package com.app.webnest.domain.dto;

import com.app.webnest.domain.dto.CommentNotificationDTO;
import com.app.webnest.domain.dto.FollowNotificationDTO;
import com.app.webnest.domain.dto.PostNotificationDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NotificationResponseDTO {
    List<PostNotificationDTO> posts;
    List<CommentNotificationDTO> comments;
    List<FollowNotificationDTO> follows;

    public NotificationResponseDTO(List<FollowNotificationDTO> notificationFollowDTO,
                                   List<CommentNotificationDTO> notificationComment,
                                   List<PostNotificationDTO> notificationPost) {
        this.posts = notificationPost;
        this.comments = notificationComment;
        this.follows = notificationFollowDTO;
    }
}
