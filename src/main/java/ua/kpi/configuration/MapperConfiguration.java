package ua.kpi.configuration;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.kpi.mapper.DocumentMapper;
import ua.kpi.mapper.FolderMapper;
import ua.kpi.mapper.ProjectMapper;

@Configuration
public class MapperConfiguration {

    @Bean
    public ProjectMapper projectMapper() {
        return Mappers.getMapper(ProjectMapper.class);
    }

    @Bean
    public FolderMapper folderMapper() {
        return Mappers.getMapper(FolderMapper.class);
    }

    @Bean
    public DocumentMapper documentMapper() {
        return Mappers.getMapper(DocumentMapper.class);
    }
}
