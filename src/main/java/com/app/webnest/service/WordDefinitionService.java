package com.app.webnest.service;

import com.app.webnest.domain.dto.WordDefinitionDTO;

public interface WordDefinitionService {
    public WordDefinitionDTO getDefinition(String word);
}
