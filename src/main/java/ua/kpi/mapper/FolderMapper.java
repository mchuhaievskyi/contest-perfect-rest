package ua.kpi.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ua.kpi.dto.FolderDto;
import ua.kpi.entity.Document;
import ua.kpi.entity.Folder;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FolderMapper {

    Folder toEntity(FolderDto folderDto);

    FolderDto toDto(Folder folder);

    @AfterMapping
    default void populateBiDirectionalRelationship(@MappingTarget Folder folder) {
        List<Document> documents = folder.getDocuments();
        if (documents != null) {
            documents.forEach(document -> document.setFolder(folder));
        }
    }
}
