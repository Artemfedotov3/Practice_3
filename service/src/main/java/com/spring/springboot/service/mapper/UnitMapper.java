package com.spring.springboot.service.mapper;

import com.spring.springboot.api.dto.UnitResponseDto;
import com.spring.springboot.service.entity.UnitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UnitMapper {
    UnitMapper INSTANCE = Mappers.getMapper(UnitMapper.class);

    // Этого метода достаточно для всех маппингов
    UnitResponseDto toDto(UnitEntity entity);
}
