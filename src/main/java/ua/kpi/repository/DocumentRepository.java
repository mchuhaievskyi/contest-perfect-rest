package ua.kpi.repository;

import org.springframework.stereotype.Repository;
import ua.kpi.entity.Document;

@Repository
public abstract class DocumentRepository extends ApplicationRepository<Document, Long> { }
