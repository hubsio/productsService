package com.example.demo.mapper;

import com.example.demo.model.entity.Accessory;
import com.example.demo.model.entity.dto.AccessoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccessoryMapper {
    @Mapping(target = "id", ignore = true)
    Accessory toEntity(AccessoryDTO accessoryDTO);
    AccessoryDTO toDTO(Accessory accessory);
}
