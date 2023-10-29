package com.impensa.expense.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tomas Kozakas
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    private String name;
    private String email;
    private String password;
    private String newPassword;
}
