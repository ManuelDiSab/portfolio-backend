package org.manuel.portfoliobe.helpers;

import org.manuel.portfoliobe.dto.ProjectRequestDto;
import org.manuel.portfoliobe.entities.Project;

import java.util.List;

public interface ProjectMapper {
    ProjectRequestDto toDto(Project project);
    List<ProjectRequestDto> toDtoList(List<Project> projects);
    Project mapDtoToEntity(ProjectRequestDto dto, Project entity);
}
