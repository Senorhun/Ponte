package org.ponte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserListInfo {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;
}
