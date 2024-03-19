package org.ponte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactLocationCreateCommand {

    @NotBlank(message = "lastName cannot be blank.")
    @NotNull(message = "lastName cannot be null.")
    @NotEmpty(message = "lastName cannot be empty.")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "lastName cannot be blank.")
    @NotNull(message = "lastName cannot be null.")
    @NotEmpty(message = "lastName cannot be empty.")
    @Column(name = "street")
    private String street;

    @NotNull(message = "lastName cannot be null.")
    @Column(name = "house_number")
    private String houseNumber;

    @NotBlank(message = "postalCode cannot be blank.")
    @NotNull(message = "postalCode cannot be null.")
    @NotEmpty(message = "postalCode cannot be empty.")
    private String postalCode;

   /* @NotBlank(message = "email cannot be blank.")
    @NotNull(message = "email cannot be null.")
    @NotEmpty(message = "email cannot be empty.")
    @Email(message = "Invalid email address.")

    */
    private String email;

    /*@NotBlank(message = "phone cannot be blank.")
    @NotNull(message = "phone cannot be null.")
    @NotEmpty(message = "phone cannot be empty.")

     */
    private String phone;

    @NotNull(message = "appUserId cannot be null.")
    private Long contactId;
}
