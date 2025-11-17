package com.app.webnest.util;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OpenAiLlmUtil {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.openai.com/v1/chat/completions")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + System.getenv("OPENAI_API_KEY"))
            .build();

    // 모델명은 사용 중인 요금제/엔드포인트에 맞게 교체
    private static final String MODEL = "gpt-4o-mini";

    public String generateDefinitionJson(String word, String lang) {
        String system = """
            You are a dictionary generator for a Korean word-chain game (끝말잇기).
            Return STRICT JSON only. No commentary. Schema:
            {
              "word": string,
              "language": "ko" | "en",
              "partOfSpeech": string,
              "definitions": string[],     // concise 1~3 items
              "examples": string[],        // <=2 items, short
              "related": string[]          // <=5 related words
            }
            Rules:
            - Keep it concise and child-friendly.
            - If the input is not a valid word, return a minimal, safe definition.
            - No newline outside JSON, no markdown, no extra text.
            """;

        String user = String.format("""
            word: "%s"
            language: "%s"
            """, word, lang);

        Map<String, Object> payload = Map.of(
                "model", MODEL,
                "temperature", 0.2,
                "response_format", Map.of("type", "json_object"),
                "messages", List.of(
                        Map.of("role","system","content", system),
                        Map.of("role","user","content", user)
                )
        );

        // 타임아웃 5초
        return webClient.post()
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofSeconds(5))
                .map(body -> {
                    // {choices:[{message:{content:"{...}"}}]}
                    List<?> choices = (List<?>) body.get("choices");
                    if (choices == null || choices.isEmpty()) {
                        return fallbackJson(word, lang);
                    }
                    Map<?,?> first = (Map<?,?>) choices.get(0);
                    Map<?,?> message = (Map<?,?>) first.get("message");
                    Object content = message.get("content");
                    return content != null ? content.toString() : fallbackJson(word, lang);
                })
                .onErrorReturn(fallbackJson(word, lang))
                .block();
    }

    private String fallbackJson(String word, String lang) {
        return String.format("""
        {
          "word": "%s",
          "language": "%s",
          "partOfSpeech": "unknown",
          "definitions": ["설명을 생성하지 못했습니다."],
          "examples": [],
          "related": []
        }
        """, escape(word), lang);
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
