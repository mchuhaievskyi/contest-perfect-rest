package ua.kpi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ua.kpi.dto.DocumentDto;
import ua.kpi.entity.Document;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentMapper {

    @Mapping(target = "id", ignore = true)
    Document toEntity(DocumentDto documentDto);

    DocumentDto toDto(Document document);
}
