package org.manuel.portfoliobe.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.manuel.portfoliobe.dto.ProjectRequestDto;
import org.manuel.portfoliobe.entities.Project;
import org.manuel.portfoliobe.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        return ResponseEntity.ok(projectService.findAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        Project project = projectService.findProjectById(id);
        if(project == null) return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(project);

    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProject(@Valid @RequestPart("project") ProjectRequestDto request,
        @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        return new ResponseEntity<>(projectService.saveProject(request, file), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        Project project = projectService.findProjectById(id);
        if(project == null) return  ResponseEntity.notFound().build();
        projectService.deleteProject(project);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @Valid @RequestPart("project") ProjectRequestDto request,
                                           @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        return new ResponseEntity<>(projectService.updateProject(id, request, file), HttpStatus.OK);
    }
}
