package com.sparta.library.dtos;

import com.sparta.library.entities.Author;
import org.mapstruct.Mapper;

// mapstruct will generate implementations of these at runtimes
@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toDTO(Author author);
    Author toEntity(AuthorDto authorDto);
}

