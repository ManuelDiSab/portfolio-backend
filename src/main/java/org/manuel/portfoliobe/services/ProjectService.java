package org.manuel.portfoliobe.services;

import org.manuel.portfoliobe.dto.ProjectRequestDto;
import org.manuel.portfoliobe.entities.Project;

import java.util.List;

public interface ProjectService {
    public List<Project> findAllProjects();
    public Project findProjectById(Long id);
    public Project saveProject(ProjectRequestDto project);
    public void deleteProject(Project project);

}
