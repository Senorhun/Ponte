package org.ponte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ponte.domain.ContactLocation;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ContactListInfo {
    private String companyName;
    private String description;
    private List<ContactLocationInfo> contactLocationList;
}
