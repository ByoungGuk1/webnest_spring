package com.app.webnest.service;

import com.app.webnest.domain.dto.WordDefinitionDTO;
import com.app.webnest.util.OpenAiLlmUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordDefinitionServiceImpl implements WordDefinitionService {
    private final OpenAiLlmUtil llmClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired(required = false)
    private StringRedisTemplate redis; // 선택

    private static final String CACHE_PREFIX = "word:def:"; // key: word:def:{lang}:{word}
    private static final Duration TTL = Duration.ofMinutes(30);

    @Override
    public WordDefinitionDTO getDefinition(String word) {
        String lang = detectLang(word); // ko/en 간단 판별
        String cacheKey = CACHE_PREFIX + lang + ":" + word;

        // 1) 캐시 히트
        if (redis != null) {
            String cached = redis.opsForValue().get(cacheKey);
            if (cached != null) {
                try {
                    return objectMapper.readValue(cached, WordDefinitionDTO.class);
                } catch (Exception ignore) {}
            }
        }

        // 2) LLM 호출
        String json = llmClient.generateDefinitionJson(word, lang);

        WordDefinitionDTO dto;
        try {
            dto = objectMapper.readValue(json, WordDefinitionDTO.class);
        } catch (Exception e) {
            // LLM이 JSON 포맷을 어겼을 때 최소 응답
            dto = WordDefinitionDTO.builder()
                    .word(word)
                    .language(lang)
                    .partOfSpeech("unknown")
                    .definitions(List.of("설명을 생성하는 데 실패했습니다. 잠시 후 다시 시도해주세요."))
                    .examples(Collections.emptyList())
                    .related(Collections.emptyList())
                    .build();
        }

        // 3) 캐시 저장
        if (redis != null) {
            try {
                redis.opsForValue().set(cacheKey, objectMapper.writeValueAsString(dto), TTL);
            } catch (Exception ignore) {}
        }
        return dto;
    }

    private String detectLang(String w) {
        // 한글 포함되면 ko, 아니면 en (간단 판별)
        return w.codePoints().anyMatch(cp -> (cp >= 0x1100 && cp <= 0x11FF) || (cp >= 0xAC00 && cp <= 0xD7A3))
                ? "ko" : "en";
    }
}
