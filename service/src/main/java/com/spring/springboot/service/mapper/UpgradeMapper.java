package com.spring.springboot.service.mapper;

import com.spring.springboot.api.dto.UpgradeResponseDto;
import com.spring.springboot.service.entity.UpgradeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UpgradeMapper {

    UpgradeMapper INSTANCE = Mappers.getMapper(UpgradeMapper.class);

    UpgradeResponseDto toDto(UpgradeEntity entity);
}
