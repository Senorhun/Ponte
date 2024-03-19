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
    @NotBlank(message = "firstName cannot be blank.")
    @NotNull(message = "firstName cannot be null.")
    @NotEmpty(message = "firstName cannot be empty.")
    private String firstName;

    @NotBlank(message = "lastName cannot be blank.")
    @NotNull(message = "lastName cannot be null.")
    @NotEmpty(message = "lastName cannot be empty.")
    private String lastName;

    @NotBlank(message = "email cannot be blank.")
    @NotNull(message = "email cannot be null.")
    @NotEmpty(message = "email cannot be empty.")
    @Email(message = "Invalid email address.")
    private String email;

    @NotBlank(message = "phone cannot be blank.")
    @NotNull(message = "phone cannot be null.")
    @NotEmpty(message = "phone cannot be empty.")
    private String phone;

    @NotNull(message = "email cannot be null.")
    private Long appUserId;

}
