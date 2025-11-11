package com.app.webnest.service.notification;

import com.app.webnest.domain.dto.CommentNotificationDTO;
import com.app.webnest.domain.dto.FollowNotificationDTO;
import com.app.webnest.domain.dto.PostNotificationDTO;
import com.app.webnest.repository.NotificationDAO;
import com.app.webnest.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationDAO notificationDAO;

    @Override
    public List<PostNotificationDTO> getPostNotificationByUserId(Long userId) {
        if(notificationDAO.getPostNotificationByUserId(userId).size() > 0){
            return notificationDAO.getPostNotificationByUserId(userId);
        }
        List<PostNotificationDTO> postNotificationDTOList = new ArrayList<PostNotificationDTO>();
        return postNotificationDTOList;
    }

    @Override
    public List<CommentNotificationDTO> getCommentNotificationByUserId(Long userId) {
        if(notificationDAO.getCommentNotificationByUserId(userId).size() > 0){
            return notificationDAO.getCommentNotificationByUserId(userId);
        }
        List<CommentNotificationDTO> commentNotificationDTOS = new ArrayList<CommentNotificationDTO>();
        return commentNotificationDTOS;
    }

    @Override
    public List<FollowNotificationDTO> getFollowNotificationByUserId(Long userId) {
        if(notificationDAO.getFollowNotificationByUserId(userId).size() > 0){
            return notificationDAO.getFollowNotificationByUserId(userId);
        }

        return new ArrayList<FollowNotificationDTO>();
    }
}
