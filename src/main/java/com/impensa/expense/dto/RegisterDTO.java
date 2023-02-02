package com.impensa.expense.dto;

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
public class RegisterDTO {
    private String name;
    private String currency;
    private String email;
    private String password;
}
