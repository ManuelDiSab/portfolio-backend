package org.manuel.portfoliobe.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProjectRequestDto {
    private String titolo;
    private String descrizione;
    private String img;
    private List<String> tech;
    private String link;
    private String github;
}
