package com.impensa.expense.dto;

import com.impensa.expense.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tomas Kozakas
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String email;
    private String currency;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
