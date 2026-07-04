package com.spring.springboot.service.mapper;

import com.spring.springboot.api.dto.ArmourResponseDto;
import com.spring.springboot.service.entity.ArmourEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArmourMapper {

    ArmourMapper INSTANCE = Mappers.getMapper(ArmourMapper.class);

    ArmourResponseDto toDto(ArmourEntity armourEntity);
}
