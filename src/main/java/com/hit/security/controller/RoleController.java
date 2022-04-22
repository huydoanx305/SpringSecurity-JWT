package com.hit.security.controller;

import com.hit.security.base.BaseController;
import com.hit.security.dto.RoleDTO;
import com.hit.security.models.Role;
import com.hit.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleController extends BaseController<Role> {
    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok().body(roleService.getRoles());
    }

    @PostMapping("/role")
    public ResponseEntity<?> createRole(@RequestBody RoleDTO roleDTO) {
        return ResponseEntity.ok().body(roleService.saveRole(roleDTO));
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }
}
