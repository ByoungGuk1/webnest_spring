package com.app.webnest.api.publicapi;

import com.app.webnest.domain.dto.ApiResponseDTO;
import com.app.webnest.domain.dto.PostSearchDTO;
import com.app.webnest.domain.vo.QuizVO;
import com.app.webnest.domain.vo.UserVO;
import com.app.webnest.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")  // 추가
@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
@Slf4j
public class SearchApi {
    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO> searchResult(@RequestParam("search") String query) {

        Map<String, Object> result = new HashMap<>();
        List<PostSearchDTO> openPosts = searchService.getOpenPostBySearchQuery(query);
        List<PostSearchDTO> questionPosts = searchService.getQuestionPostBySearchQuery(query);
        List<QuizVO> quizzes = searchService.getQuizBySearchQuery(query);
        List<UserVO> users = searchService.getUserBySearchQuery(query);

        result.put("search", query);
        result.put("openPosts", openPosts);
        result.put("questionPosts", questionPosts);
        result.put("quizzes", quizzes);
        result.put("users", users);

        return ResponseEntity.ok(ApiResponseDTO.of("검색 결과", result));
    }
}
