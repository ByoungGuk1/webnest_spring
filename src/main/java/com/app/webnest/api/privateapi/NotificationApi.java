package com.app.webnest.api.privateapi;

import com.app.webnest.domain.dto.ApiResponseDTO;
import com.app.webnest.domain.dto.CommentNotificationDTO;
import com.app.webnest.domain.dto.FollowNotificationDTO;
import com.app.webnest.domain.dto.NotificationResponseDTO;
import com.app.webnest.domain.dto.PostNotificationDTO;
import com.app.webnest.service.notification.NotificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/notification/*")
public class NotificationApi {
    private final NotificationServiceImpl notificationService;

    @PostMapping("get-notification")
    public ResponseEntity<ApiResponseDTO> getNotification(@RequestBody Long userId){
        List<PostNotificationDTO> foundPosts = notificationService.getPostNotificationByUserId(userId);
        List<FollowNotificationDTO> foundFollows = notificationService.getFollowNotificationByUserId(userId);
        List<CommentNotificationDTO>  foundComments = notificationService.getCommentNotificationByUserId(userId);
        NotificationResponseDTO notificationResponseDTO = new NotificationResponseDTO(foundFollows, foundComments, foundPosts);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("newNotifications", notificationResponseDTO));
    }
}
