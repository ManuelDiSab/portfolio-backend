package org.manuel.portfoliobe.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.manuel.portfoliobe.entities.SkillCategory;

@Data
public class SkillRequestDto {
    @NotBlank(message = "Il nome della skill è obbligatorio")
    private String name;
    @NotBlank(message = "La categoria è obbligatoria")
    private SkillCategory category;
}
