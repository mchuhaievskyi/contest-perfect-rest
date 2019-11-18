package ua.kpi.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.dto.ProjectDto;
import ua.kpi.entity.Project;
import ua.kpi.mapper.ProjectMapper;
import ua.kpi.repository.ProjectRepository;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectService {

    private ProjectMapper projectMapper;
    private ProjectRepository projectRepository;

    public Long create(ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        projectRepository.save(project);
        return project.getId();
    }

    public void update(ProjectDto projectDto, Long projectId) {
        projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);

        Project project = projectMapper.toEntity(projectDto);
        project.setId(projectId);
        projectRepository.save(project);
    }

    public ProjectDto read(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);
        return projectMapper.toDto(project);
    }

    public void delete(Long projectId) {
        projectRepository.deleteById(projectId);
    }
}
