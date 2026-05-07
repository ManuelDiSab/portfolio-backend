package org.manuel.portfoliobe.helpers;

import org.manuel.portfoliobe.dto.SkillRequestDto;
import org.manuel.portfoliobe.entities.Skill;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillMapperImpl implements SkillMapper {
    @Override
    public SkillRequestDto toDto(Skill skill) {
        SkillRequestDto dto = new SkillRequestDto();
        dto.setName(skill.getName());
        dto.setCategory(skill.getCategory());
        return dto;
    }

    @Override
    public List<SkillRequestDto> toDtoList(List<Skill> skills) {
        return skills.stream().map(this::toDto).toList();
    }

    @Override
    public Skill mapDtoToEntity(SkillRequestDto dto, Skill entity) {
        entity.setName(dto.getName());
        entity.setCategory(dto.getCategory());
        return entity;
    }
}
