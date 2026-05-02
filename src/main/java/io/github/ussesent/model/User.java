package io.github.ussesent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    public enum UserRole {
        USER,
        ADMIN,
        OWNER
    }

    private int id;
    private String username;
    private String password;
    private UserRole role;

}
