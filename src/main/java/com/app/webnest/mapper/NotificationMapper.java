package com.app.webnest.mapper;

import com.app.webnest.domain.dto.CommentNotificationDTO;
import com.app.webnest.domain.dto.FollowNotificationDTO;
import com.app.webnest.domain.dto.PostNotificationDTO;
import com.app.webnest.domain.dto.PostSearchDTO;
import com.app.webnest.domain.vo.QuizVO;
import com.app.webnest.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {

    //                  알람
//    post에 대한 알림
    public List<PostNotificationDTO> selectPostNotificationByUserId(Long receiverUserId);
    //    comment 알림 test 1L / 3L
    public List<CommentNotificationDTO> selectCommentNotificationByUserId(Long receiverUserId);
    //    newFollow 알림 test 1L
    public List<FollowNotificationDTO> selectFollowNotificationByUserId(Long receiverUserId);
}
