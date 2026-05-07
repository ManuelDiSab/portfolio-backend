package org.manuel.portfoliobe.services;

import org.manuel.portfoliobe.dto.SkillRequestDto;
import org.manuel.portfoliobe.entities.Skill;
import org.manuel.portfoliobe.entities.SkillCategory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SkillService {
    List<Skill> findAllSkills();
    Skill saveSkill(SkillRequestDto dto, MultipartFile file) throws IOException;
    Skill updateSkill(Long id,SkillRequestDto dto, MultipartFile file) throws IOException;
    Skill getSkillById(Long id);
    void deleteSkill(Long id);
    List<Skill> findSkillsByCategory(SkillCategory category);
}
