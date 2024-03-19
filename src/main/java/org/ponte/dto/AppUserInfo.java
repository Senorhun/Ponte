package org.ponte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserInfo {
    private String firstName;
    private String lastName;
    private String email;
    private String extraInfo;
    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;
}
