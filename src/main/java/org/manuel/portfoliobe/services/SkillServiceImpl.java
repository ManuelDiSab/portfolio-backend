package org.manuel.portfoliobe.services;

import lombok.RequiredArgsConstructor;
import org.manuel.portfoliobe.dto.SkillRequestDto;
import org.manuel.portfoliobe.entities.Skill;
import org.manuel.portfoliobe.entities.SkillCategory;
import org.manuel.portfoliobe.helpers.SkillMapper;
import org.manuel.portfoliobe.repositories.SkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final FileService fileService;
    private final SkillMapper skillMapper;

    @Override
    public List<Skill> findAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill saveSkill(SkillRequestDto dto, MultipartFile file) throws IOException {
        Skill skill = new Skill();
        skillMapper.mapDtoToEntity(dto, skill);
        if(file != null && !file.isEmpty()) {
            String fileName = fileService.saveFile(file);
            skill.setImg(fileName);
        }
        return skillRepository.save(skill);
    }

    @Override
    public Skill updateSkill(Long id,SkillRequestDto dto, MultipartFile file) throws IOException {
        Skill skill = skillRepository.findById(id).orElseThrow( () -> new RuntimeException( "Skill con id " + id + " non trovato" ) );
        skillMapper.mapDtoToEntity(dto, skill);
        if(file != null && !file.isEmpty()) {
            String fileName = fileService.saveFile(file);
            skill.setImg(fileName);
        }
        return skillRepository.save(skill);
    }

    @Override
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public List<Skill> findSkillsByCategory(SkillCategory category) {
        return skillRepository.findByCategory(category);
    }
}
