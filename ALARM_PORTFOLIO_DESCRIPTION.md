# 알람 기능 포트폴리오 설명

## 1. 알람 액션 타입 Enum 관리 + 동적 쿼리

**파일**: `src/main/java/com/app/webnest/domain/enums/NotificationActionType.java`
- **설명**: 알람 타입(좋아요, 댓글, 답글)을 Enum으로 관리하여 타입 안정성과 확장성 확보
- **동적 쿼리 활용**: `NotificationActionType` enum 값을 기반으로 동적으로 WHERE 조건을 구성하여 특정 액션 타입의 알람만 필터링 가능

**예시**:
```xml
<if test="notificationActionType != null">
    AND POST_NOTIFICATION_ACTION = #{notificationActionType}
</if>
```

---

## 3. JOIN을 통한 효율적인 데이터 조회 + 동적 쿼리

**파일**: `src/main/resources/mapper/notificationMapper.xml`
- **라인**: 7-21줄 (selectPostNotificationByUserId), 22-39줄 (selectCommentNotificationByUserId), 40-48줄 (selectFollowNotificationByUserId)
- **설명**: 알람 테이블과 사용자, 게시글, 댓글 테이블을 JOIN하여 한 번의 쿼리로 필요한 정보를 효율적으로 조회
- **동적 쿼리 활용**: 
  - 읽음/안읽음 상태에 따라 동적으로 WHERE 조건 추가
  - 알람 타입별로 필요한 JOIN만 동적으로 추가하여 불필요한 조인 최소화
  - 날짜 범위 필터링을 동적으로 적용

**예시**:
```xml
<where>
    RECEIVER_USER_ID = #{receiverUserId}
    <if test="isRead != null">
        AND POST_NOTIFICATION_IS_READ = #{isRead}
    </if>
    <if test="startDate != null">
        AND NOTIFICATION_CREATE_AT >= #{startDate}
    </if>
    <if test="endDate != null">
        AND NOTIFICATION_CREATE_AT &lt;= #{endDate}
    </if>
</where>
```

---

## 4. 통합 알람 조회 API + 동적 쿼리

**파일**: `src/main/java/com/app/webnest/api/privateapi/NotificationApi.java`
- **라인**: 27-39줄 (getNotification 메서드)
- **설명**: 한 번의 API 호출로 모든 타입의 알람(팔로우, 댓글, 게시글)을 통합 조회
- **동적 쿼리 활용**:
  - 요청 파라미터에 따라 동적으로 알람 타입 필터링
  - 읽음/안읽음 상태에 따른 조건부 조회
  - 알람 액션 타입(NotificationActionType enum)에 따른 동적 필터링
  - 하나의 쿼리로 여러 조건을 동적으로 조합하여 유연한 검색 지원

**예시**:
```xml
<select id="selectNotificationsByConditions" resultType="NotificationDTO">
    SELECT ...
    FROM TBL_POST_NOTIFICATION
    <where>
        RECEIVER_USER_ID = #{receiverUserId}
        <if test="notificationType != null">
            AND POST_NOTIFICATION_ACTION = #{notificationType}
        </if>
        <if test="isRead != null">
            AND POST_NOTIFICATION_IS_READ = #{isRead}
        </if>
        <if test="startDate != null">
            AND NOTIFICATION_CREATE_AT >= #{startDate}
        </if>
    </where>
</select>
```

---

## 동적 쿼리 사용의 장점

1. **유연한 필터링**: 클라이언트 요구사항에 따라 다양한 조건으로 알람 조회 가능
2. **성능 최적화**: 필요한 조건만 동적으로 추가하여 불필요한 조인이나 조건 제거
3. **코드 재사용성**: 하나의 쿼리로 여러 시나리오 처리 가능
4. **유지보수성**: 조건 추가/제거가 용이하여 확장성 향상

