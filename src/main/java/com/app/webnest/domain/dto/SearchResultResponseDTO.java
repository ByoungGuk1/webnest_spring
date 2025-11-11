package com.app.webnest.domain.dto;

import com.app.webnest.domain.dto.PostSearchDTO;
import com.app.webnest.domain.vo.PostVO;
import com.app.webnest.domain.vo.QuizVO;
import com.app.webnest.domain.vo.UserVO;
import lombok.*;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
@ToString
public class SearchResultResponseDTO {
    private Integer total;
    private List<QuizVO> quizzes;
    private List<PostSearchDTO> openPosts;
    private List<PostSearchDTO> questionPosts;
    private List<UserVO> users;
}
