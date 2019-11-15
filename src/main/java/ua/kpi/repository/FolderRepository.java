package ua.kpi.repository;

import org.springframework.stereotype.Repository;
import ua.kpi.entity.Folder;

@Repository
public abstract class FolderRepository extends ApplicationRepository<Folder, Long> { }
