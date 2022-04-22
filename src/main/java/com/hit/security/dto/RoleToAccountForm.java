package com.hit.security.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleToAccountForm {
    private String username;
    private String roleName;
}