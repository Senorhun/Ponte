package org.ponte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactLocationListInfo {
    private String postalCode;
    private String city;
    private String street;
    private String houseNumber;
    private String email;
    private String phone;
}
