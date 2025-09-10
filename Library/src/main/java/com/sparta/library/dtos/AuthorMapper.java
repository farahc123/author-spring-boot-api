package com.sparta.library.dtos;

import com.sparta.library.entities.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface AuthorMapper {
    // Map books field using BookMapper, prevent recursion by not mapping author in BookDto
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "books", source = "books")
    @Mapping(target = "email", source = "email")
    AuthorDto toDTO(Author author);


    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "books", source = "books")
    Author toEntity(AuthorDto authorDto);
}