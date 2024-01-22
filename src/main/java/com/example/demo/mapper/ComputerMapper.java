package com.example.demo.mapper;

import com.example.demo.model.entity.Computer;
import com.example.demo.model.entity.dto.ComputerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ComputerMapper {
    @Mapping(target = "id", ignore = true)
    Computer toEntity(ComputerDTO computerDTO);
    ComputerDTO toDTO(Computer computer);
}
