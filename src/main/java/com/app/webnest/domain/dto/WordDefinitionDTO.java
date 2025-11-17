package com.app.webnest.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WordDefinitionDTO {
    private String word;
    private String language;           // "ko" | "en" 등
    private String partOfSpeech;       // 명사/동사/형용사...
    private List<String> definitions;  // 간결 정의 1~3개
    private List<String> examples;     // 예문 0~2개
    private List<String> related;      // 연관어 0~5개
}
