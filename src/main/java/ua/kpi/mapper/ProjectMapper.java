package ua.kpi.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ua.kpi.dto.ProjectDto;
import ua.kpi.entity.Document;
import ua.kpi.entity.Folder;
import ua.kpi.entity.Project;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {

    @Mapping(target = "id", ignore = true)
    Project toEntity(ProjectDto projectDto);

    ProjectDto toDto(Project project);

    @AfterMapping
    default void populateBiDirectionalRelationship(@MappingTarget Project project) {
        List<Folder> folders = project.getFolders();
        if (folders != null) {
            folders.forEach(folder -> {
                folder.setProject(project);
                List<Document> documents = folder.getDocuments();
                if (documents != null) {
                    documents.forEach(document -> document.setFolder(folder));
                }
            });
        }
    }
}
