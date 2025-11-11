package com.app.webnest.service;

import com.app.webnest.domain.dto.CommentNotificationDTO;
import com.app.webnest.domain.dto.FollowNotificationDTO;
import com.app.webnest.domain.dto.PostNotificationDTO;

import java.util.List;

public interface NotificationService {
    public List<PostNotificationDTO> getPostNotificationByUserId(Long userId);
    public List<CommentNotificationDTO> getCommentNotificationByUserId(Long userId);
    public List<FollowNotificationDTO> getFollowNotificationByUserId(Long userId);
}
