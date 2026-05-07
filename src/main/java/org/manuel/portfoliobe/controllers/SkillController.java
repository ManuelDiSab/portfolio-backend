package org.manuel.portfoliobe.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.manuel.portfoliobe.dto.ProjectRequestDto;
import org.manuel.portfoliobe.dto.SkillRequestDto;
import org.manuel.portfoliobe.entities.Project;
import org.manuel.portfoliobe.entities.Skill;
import org.manuel.portfoliobe.entities.SkillCategory;
import org.manuel.portfoliobe.services.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @GetMapping
    public ResponseEntity<?> getAllSkills() {
        return ResponseEntity.ok(skillService.findAllSkills());
    }

    @GetMapping("/{category}")
    public ResponseEntity<?> getSkillsByCategory(@PathVariable SkillCategory category) {
        return ResponseEntity.ok(skillService.findSkillsByCategory(category));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllSkillsByCategory(@PathVariable Long id) {
        Skill skill = skillService.getSkillById(id);
        if(skill == null) return  ResponseEntity.notFound().build();
        return new ResponseEntity<>(skillService.getSkillById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createSkill(@Valid @RequestPart("skill") SkillRequestDto request,
                                         @RequestPart(value = "file") MultipartFile file ) throws IOException {
        return new ResponseEntity<>(skillService.saveSkill(request, file), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSkill(@PathVariable Long id) {
        Skill skill = skillService.getSkillById(id);
        if(skill == null) return  ResponseEntity.notFound().build();
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateSkill(@PathVariable Long id, @Valid @RequestPart("skill") SkillRequestDto request,
                                         @RequestPart("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(skillService.updateSkill(id, request, file), HttpStatus.OK);
    }

}
