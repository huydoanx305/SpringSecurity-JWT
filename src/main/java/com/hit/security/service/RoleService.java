package com.hit.security.service;

import com.hit.security.dto.RoleDTO;
import com.hit.security.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();
    Role saveRole(RoleDTO roleDTO);
    void deleteRole(Long idRole);
}
