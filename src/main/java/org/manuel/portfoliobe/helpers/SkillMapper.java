package org.manuel.portfoliobe.helpers;

import org.manuel.portfoliobe.dto.SkillRequestDto;
import org.manuel.portfoliobe.entities.Skill;

import java.util.List;

public interface SkillMapper {
    SkillRequestDto toDto(Skill skill);
    List<SkillRequestDto> toDtoList(List<Skill> skills);
    Skill mapDtoToEntity(SkillRequestDto dto, Skill entity);
}
