package org.manuel.portfoliobe.services;

import lombok.RequiredArgsConstructor;
import org.manuel.portfoliobe.dto.ProjectRequestDto;
import org.manuel.portfoliobe.entities.Project;
import org.manuel.portfoliobe.repositories.ProjectRepository;
import org.manuel.portfoliobe.helpers.ProjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor  // Generate the constructor for all the 'final' fields | Genera il costruttore per tutti i campi 'final'
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final FileService fileService;

    // DATABASE METHODS
    @Override
    public List<Project> findAllProjects() {
        return  projectRepository.findAll();
    }

    @Override
    public Project findProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project saveProject(ProjectRequestDto dto, MultipartFile file) throws IOException {
        Project project = new Project();
        projectMapper.mapDtoToEntity(dto, project);
        if(file != null && !file.isEmpty()) {
            String fileName = fileService.saveFile(file);
            project.setImg(fileName);
        }
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Long id, ProjectRequestDto dto, MultipartFile file) throws IOException {
        Project project = projectRepository.findById(id).orElseThrow( () -> new RuntimeException( "Progetto con id " + id + " non trovato" ) );
        projectMapper.mapDtoToEntity(dto, project);
        if(file != null && !file.isEmpty()) {
             String fileName = fileService.saveFile(file);
             project.setImg(fileName);
         }
         return projectRepository.save(project);
    }


    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }


}
