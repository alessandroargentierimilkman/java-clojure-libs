package com.milkman.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor @NoArgsConstructor
@Data
@Accessors(fluent = true, chain = true)
public class DriverDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
