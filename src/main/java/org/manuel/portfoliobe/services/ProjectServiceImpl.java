package org.manuel.portfoliobe.services;

import lombok.RequiredArgsConstructor;
import org.manuel.portfoliobe.dto.ProjectRequestDto;
import org.manuel.portfoliobe.entities.Project;
import org.manuel.portfoliobe.repositories.ProjectRepository;
import org.manuel.portfoliobe.helpers.ProjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor  // Generate the constructor for all the 'final' fields | Genera il costruttore per tutti i campi 'final'
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    // DATABASE METHODS
    @Override
    public List<Project> findAllProjects() {
        return  projectRepository.findAll();
    }

    @Override
    public Project findProjectById(Long id) {
        return null;
    }

    @Override
    public Project saveProject(ProjectRequestDto dto) {
        Project project = new Project();
        project = projectMapper.mapDtoToEntity(dto, project);
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }


}
