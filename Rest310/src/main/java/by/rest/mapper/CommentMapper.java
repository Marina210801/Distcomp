// src/main/java/by/rest/mapper/CommentMapper.java
package by.rest.mapper;

import by.rest.domain.Comment;
import by.rest.dto.CommentRequestTo;
import by.rest.dto.CommentResponseTo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toEntity(CommentRequestTo dto);
    CommentResponseTo toResponse(Comment entity);
}
