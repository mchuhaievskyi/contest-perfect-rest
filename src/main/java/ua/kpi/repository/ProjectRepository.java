package ua.kpi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.entity.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> { }
