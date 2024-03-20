package org.ponte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {
    private String companyName;
    private String description;
    private List<ContactLocationInfo> contactLocationList;

}


