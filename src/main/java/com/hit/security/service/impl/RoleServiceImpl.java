package com.hit.security.service.impl;

import com.hit.security.dto.RoleDTO;
import com.hit.security.models.Role;
import com.hit.security.repository.RoleRepository;
import com.hit.security.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(RoleDTO roleDTO) {
        return roleRepository.save(mapper.map(roleDTO, Role.class));
    }


    @Override
    public void deleteRole(Long idRole) {
        Optional<Role> role = roleRepository.findById(idRole);
        roleRepository.delete(role.get());
    }
}
