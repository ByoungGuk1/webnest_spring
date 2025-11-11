package com.app.webnest.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class TokenDTO {
    private Long userId;
    private String accessToken;
    private String refreshToken;
}
