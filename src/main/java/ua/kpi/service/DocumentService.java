package ua.kpi.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.dto.DocumentDto;
import ua.kpi.entity.Document;
import ua.kpi.entity.Folder;
import ua.kpi.mapper.DocumentMapper;
import ua.kpi.repository.DocumentRepository;
import ua.kpi.repository.FolderRepository;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentService {

    private DocumentMapper documentMapper;
    private FolderRepository folderRepository;
    private DocumentRepository documentRepository;

    public Long create(DocumentDto documentDto, Long folderId) {
        Folder folder = folderRepository.read(folderId);
        Document document = documentMapper.toEntity(documentDto);
        folder.addDocument(document);
        documentRepository.save(document);
        return document.getId();
    }

    public void update(DocumentDto documentDto,  Long documentId) {
        documentRepository.read(documentId);
        Document document = documentMapper.toEntity(documentDto);
        document.setId(documentId);
        documentRepository.save(document);
    }

    public DocumentDto read(Long documentId) {
        Document document = documentRepository.read(documentId);
        return documentMapper.toDto(document);
    }

    public void delete(Long documentId) {
        Document document = documentRepository.read(documentId);
        document.getFolder().deleteDocument(document);
        documentRepository.deleteById(documentId);
    }
}
