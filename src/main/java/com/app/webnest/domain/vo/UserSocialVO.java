package com.app.webnest.domain.vo;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class UserSocialVO {
  private Long id;
  private String userSocialProviderId;
  private String userSocialProvider;
  private Long userId;
}
