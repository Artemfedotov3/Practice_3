package com.spring.springboot.service.mapper;

import com.spring.springboot.api.dto.ExoticBeastResponseDto;
import com.spring.springboot.service.entity.ExoticBeastEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExoticBeastMapper {

    ExoticBeastMapper INSTANCE = Mappers.getMapper(ExoticBeastMapper.class);

    ExoticBeastResponseDto toDto(ExoticBeastEntity entity);
}
