package org.manuel.portfoliobe.controllers;

import lombok.RequiredArgsConstructor;
import org.manuel.portfoliobe.dto.ProjectRequestDto;
import org.manuel.portfoliobe.entities.Project;
import org.manuel.portfoliobe.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProject(@RequestBody ProjectRequestDto request) {
        return new ResponseEntity<>(projectService.saveProject(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        Project project = projectService.findProjectById(id);
        if(project == null) return  ResponseEntity.notFound().build();
        projectService.deleteProject(project);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody ProjectRequestDto request) {
        Project project = projectService.findProjectById(id);
        if(project == null) return   ResponseEntity.notFound().build();
        project = projectService.saveProject(request);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
}
