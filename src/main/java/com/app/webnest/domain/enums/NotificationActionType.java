package com.app.webnest.domain.enums;

public enum NotificationActionType {
    NEW_LIKE("New Like"),      // 좋아요
    COMMENT("COMMENT"),        // 댓글
    NEW_REPLY("New Reply");    // 답글 (추후 사용 가능)
    
    private final String value;
    
    NotificationActionType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static NotificationActionType fromValue(String value) {
        if (value == null) {
            return null;
        }
        for (NotificationActionType type : NotificationActionType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return null;
    }
}

