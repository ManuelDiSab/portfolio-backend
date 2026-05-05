package org.manuel.portfoliobe.services;

import org.manuel.portfoliobe.dto.ProjectRequestDto;
import org.manuel.portfoliobe.entities.Project;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProjectService {
    public List<Project> findAllProjects();
    public Project findProjectById(Long id);
    public Project saveProject(ProjectRequestDto project, MultipartFile file) throws IOException;
    public Project updateProject(Long id, ProjectRequestDto project, MultipartFile file) throws IOException;
    public void deleteProject(Project project);

}
