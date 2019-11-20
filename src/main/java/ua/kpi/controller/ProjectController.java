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
import ua.kpi.dto.FolderDto;
import ua.kpi.dto.IdDto;
import ua.kpi.dto.ProjectDto;
import ua.kpi.service.FolderService;
import ua.kpi.service.ProjectService;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/projects", produces = APPLICATION_JSON_VALUE)
@CrossOrigin
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {

    private ProjectService projectService;
    private FolderService folderService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<IdDto> create(@RequestBody @Valid ProjectDto projectDto) {
        Long projectId = projectService.create(projectDto);
        return new ResponseEntity<>(new IdDto(projectId), HttpStatus.OK);
    }

    @PostMapping(value = "/{projectId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody @Valid ProjectDto projectDto, @PathVariable Long projectId) {
        projectService.update(projectDto, projectId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDto> read(@PathVariable Long projectId) {
        ProjectDto projectDto = projectService.read(projectId);
        return new ResponseEntity<>(projectDto, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity delete(@PathVariable Long projectId) {
        projectService.delete(projectId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{projectId}/folders")
    public ResponseEntity<IdDto> createFolder(@RequestBody @Valid FolderDto folderDto, @PathVariable Long projectId) {
        Long folderId = folderService.create(folderDto, projectId);
        return new ResponseEntity<>(new IdDto(folderId), HttpStatus.OK);
    }
}
