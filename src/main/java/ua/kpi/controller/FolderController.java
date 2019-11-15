package ua.kpi.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kpi.dto.DocumentDto;
import ua.kpi.dto.FolderDto;
import ua.kpi.dto.IdDto;
import ua.kpi.service.DocumentService;
import ua.kpi.service.FolderService;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/folders", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FolderController {

    private FolderService folderService;
    private DocumentService documentService;

    @PostMapping(value = "/{folderId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody @Valid FolderDto folderDto, @PathVariable Long folderId) {
        folderService.update(folderDto, folderId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{folderId}")
    public ResponseEntity<FolderDto> read(@PathVariable Long folderId) {
        FolderDto folderDto = folderService.read(folderId);
        return new ResponseEntity<>(folderDto, HttpStatus.OK);
    }

    @DeleteMapping("/{folderId}")
    public ResponseEntity delete(@PathVariable Long folderId) {
        folderService.delete(folderId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{folderId}/documents")
    public ResponseEntity<IdDto> createDocument(@RequestBody @Valid DocumentDto documentDto, @PathVariable Long folderId) {
        Long documentId = documentService.create(documentDto, folderId);
        return new ResponseEntity<>(new IdDto(documentId), HttpStatus.OK);
    }
}
