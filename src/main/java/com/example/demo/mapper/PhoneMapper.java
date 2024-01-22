package com.example.demo.mapper;

import com.example.demo.model.entity.Phone;
import com.example.demo.model.entity.dto.PhoneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhoneMapper {
    @Mapping(target = "id", ignore = true)
    Phone toEntity(PhoneDTO phoneDTO);
    PhoneDTO toDTO(Phone phone);
}
