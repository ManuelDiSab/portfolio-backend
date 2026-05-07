package org.manuel.portfoliobe.helpers;


import org.manuel.portfoliobe.dto.ProjectRequestDto;
import org.manuel.portfoliobe.entities.Project;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectRequestDto toDto(Project project) {
        if(project==null){ return null; }
        ProjectRequestDto dto = new ProjectRequestDto();
        dto.setTitolo(project.getTitolo());
        dto.setDescrizione(project.getDescrizione());
        dto.setLink(project.getLink());
        dto.setTech(project.getTech());
        dto.setGithub(project.getGithub());
        return dto;
    }

    @Override
    public List<ProjectRequestDto> toDtoList(List<Project> projects) {
        if(projects==null){ return null; }
        return projects.stream().map(this::toDto).toList();
    }

    @Override
    public Project mapDtoToEntity(ProjectRequestDto dto, Project entity) {
        entity.setTitolo(dto.getTitolo());
        entity.setDescrizione(dto.getDescrizione());
        entity.setTech(dto.getTech());
        entity.setLink(dto.getLink());
        entity.setGithub(dto.getGithub());
        return entity;
    }
}


