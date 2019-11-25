package ua.kpi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.entity.Document;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> { }
