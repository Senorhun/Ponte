package org.ponte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}


