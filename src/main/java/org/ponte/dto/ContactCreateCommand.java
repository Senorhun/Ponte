package org.ponte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactCreateCommand {
    @NotBlank(message = "companyName cannot be blank.")
    @NotNull(message = "companyName cannot be null.")
    @NotEmpty(message = "companyName cannot be empty.")
    private String companyName;

    @NotBlank(message = "description cannot be blank.")
    @NotNull(message = "description cannot be null.")
    @NotEmpty(message = "description cannot be empty.")
    private String description;


    @NotNull(message = "appUserId cannot be null.")
    private Long appUserId;

}
