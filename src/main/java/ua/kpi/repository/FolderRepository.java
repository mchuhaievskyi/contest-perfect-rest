package ua.kpi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.entity.Folder;

@Repository
public interface FolderRepository extends CrudRepository<Folder, Long> { }
