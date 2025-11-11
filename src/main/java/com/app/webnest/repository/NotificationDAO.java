package com.app.webnest.repository;

import com.app.webnest.domain.dto.CommentNotificationDTO;
import com.app.webnest.domain.dto.FollowNotificationDTO;
import com.app.webnest.domain.dto.PostNotificationDTO;
import com.app.webnest.mapper.NotificationMapper;
import com.app.webnest.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class NotificationDAO {
    private final NotificationMapper notificationMapper;

    public List<PostNotificationDTO> getPostNotificationByUserId(Long userId) {
        return notificationMapper.selectPostNotificationByUserId(userId);
    }

    public List<CommentNotificationDTO> getCommentNotificationByUserId(Long userId) {
        return notificationMapper.selectCommentNotificationByUserId(userId);
    }
    public List<FollowNotificationDTO> getFollowNotificationByUserId(Long userId) {
        return notificationMapper.selectFollowNotificationByUserId(userId);
    }
}
