// src/main/java/by/rest/mapper/EditorMapper.java
package by.rest.mapper;

import by.rest.domain.Editor;
import by.rest.dto.EditorRequestTo;
import by.rest.dto.EditorResponseTo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EditorMapper {
    Editor toEntity(EditorRequestTo dto);
    EditorResponseTo toResponse(Editor entity);
}

