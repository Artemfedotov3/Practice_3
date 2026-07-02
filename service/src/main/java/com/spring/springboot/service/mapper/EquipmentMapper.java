package com.spring.springboot.service.mapper;

import com.spring.springboot.api.dto.EquipmentResponseDto;
import com.spring.springboot.service.entity.EquipmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    EquipmentMapper INSTANCE = Mappers.getMapper(EquipmentMapper.class);

    EquipmentResponseDto toDto(EquipmentEntity entity);
}
