package org.manuel.portfoliobe.repositories;

import org.manuel.portfoliobe.entities.Skill;
import org.manuel.portfoliobe.entities.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByCategory(SkillCategory category);
}
