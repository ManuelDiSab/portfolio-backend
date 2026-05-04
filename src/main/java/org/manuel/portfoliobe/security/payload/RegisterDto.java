package org.manuel.portfoliobe.security.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String name;
    private String username;

    @NotBlank
    @Email
    private String email;
    private String password;
    // Passagio di ruoli dal client (Facoltativo)
    private Set<String> roles;
}

// Il client dovrà inviare un oggetto JSON nel body con questa forma
/*{
    "name": "Francesca Neri",
    "username": "francescaneri",
    "email": "f.neri@example.com",
    "password": "qwerty",
    "roles": ["INSTRUCTOR", "CLIENT"] // Facoltativo
}*/
