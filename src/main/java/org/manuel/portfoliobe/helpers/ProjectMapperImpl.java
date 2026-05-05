package org.manuel.portfoliobe.helpers;


import org.manuel.portfoliobe.dto.ProjectRequestDto;
import org.manuel.portfoliobe.entities.Project;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectRequestDto toDto(Project project) {
        return null;
    }

    @Override
    public List<ProjectRequestDto> toDtoList(List<Project> projects) {
        return List.of();
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


