package org.manuel.portfoliobe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public class ProjectRequestDto {
    @NotBlank(message = "Il titolo è obbligatorio")
    private String titolo;
    @NotBlank(message = "La descrizione è obbligatoria")
    @Size(min = 50, message = "La descrizione deve esser almeno di 50 caratteri")
    private String descrizione;

    @NotEmpty(message = "Devi inserire almeno una tecnologia")
    private List<String> tech;

    private String link;

    @URL(message = "L'URL deve essere valido")
    @NotBlank(message = "Il link alla repository è  obbligatorio")
    private String github;
}
