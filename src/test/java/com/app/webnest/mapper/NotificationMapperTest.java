package com.app.webnest.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class NotificationMapperTest {

    @Autowired
    private NotificationMapper notificationMapper;

    @Test
    public void mapperTest(){
        log.info("select Test USER::::::{}", notificationMapper.selectCommentNotificationByUserId(1L));

    }
}