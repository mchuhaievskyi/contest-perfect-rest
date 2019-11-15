package ua.kpi.repository;

import org.springframework.stereotype.Repository;
import ua.kpi.entity.Project;

@Repository
public abstract class ProjectRepository extends ApplicationRepository<Project, Long> { }
