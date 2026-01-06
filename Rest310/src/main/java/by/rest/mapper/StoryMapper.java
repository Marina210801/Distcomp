// src/main/java/by/rest/mapper/StoryMapper.java
package by.rest.mapper;

import by.rest.domain.Story;
import by.rest.dto.StoryRequestTo;
import by.rest.dto.StoryResponseTo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoryMapper {
    Story toEntity(StoryRequestTo dto);
    StoryResponseTo toResponse(Story entity);
}

