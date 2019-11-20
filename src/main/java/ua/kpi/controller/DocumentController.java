package ua.kpi.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kpi.dto.DocumentDto;
import ua.kpi.service.DocumentService;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/documents", produces = APPLICATION_JSON_VALUE)
@CrossOrigin
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentController {

    private DocumentService documentService;

    @PostMapping(value = "/{documentId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody @Valid DocumentDto documentDto, @PathVariable Long documentId) {
        documentService.update(documentDto, documentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentDto> read(@PathVariable Long documentId) {
        DocumentDto documentDto = documentService.read(documentId);
        return new ResponseEntity<>(documentDto, HttpStatus.OK);
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity delete(@PathVariable Long documentId) {
        documentService.delete(documentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
