// src/main/java/by/rest/mapper/TagMapper.java
package by.rest.mapper;

import by.rest.domain.Tag;
import by.rest.dto.TagRequestTo;
import by.rest.dto.TagResponseTo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag toEntity(TagRequestTo dto);
    TagResponseTo toResponse(Tag entity);
}

