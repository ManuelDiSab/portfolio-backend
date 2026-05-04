package org.manuel.portfoliobe.security.payload;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDto {
    private String username;
    private String password;
}

//Il client dovrà inviare un oggetto JSON nel body con questa forma
/*{
    "username": "mariorossi",
    "password": "Pa$$w0rd!"
}*/
