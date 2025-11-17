package com.app.webnest.api.privateapi;

import com.app.webnest.domain.dto.ApiResponseDTO;
import com.app.webnest.domain.dto.WordDefinitionDTO;
import com.app.webnest.service.WordDefinitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/word")
@RequiredArgsConstructor
public class WordDefinitionApi {
    private final WordDefinitionService wordDefinitionService;

    @GetMapping("/definition")
    public ResponseEntity<ApiResponseDTO<WordDefinitionDTO>> getDefinition(
            @RequestParam("word") String word) {

        if (word == null || word.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseDTO.of("word 파라미터는 필수입니다.", null));
        }

        WordDefinitionDTO dto = wordDefinitionService.getDefinition(word.trim());
        return ResponseEntity.ok(ApiResponseDTO.of("단어 설명 생성 완료", dto));
    }
}
