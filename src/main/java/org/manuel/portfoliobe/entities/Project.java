package org.manuel.portfoliobe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")

/**
 * EN: Entity for the projects in my Portfolio website
 * IT: Entity per i progetti nel mio Portfolio
 */

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false,  unique = true)
    private Long id;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private String img;

    @ElementCollection
    @CollectionTable(name = "project_tech",
            joinColumns = @JoinColumn(name = "project_id"))
    private List<String> tech;

    private String link;
    @Column(nullable = false)
    private String github;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
