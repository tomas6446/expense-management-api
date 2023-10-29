package com.impensa.expense.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class DashboardDTO {
    @JsonProperty("user_name")
    private String name;

    @JsonProperty("user_email")
    private String email;

    @JsonProperty("user_currency")
    private String currency;
}
