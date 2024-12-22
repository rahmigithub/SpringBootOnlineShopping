package org.rahmi.springbootonlineshopping.service.Impl;

import org.rahmi.springbootonlineshopping.model.Role;
import org.rahmi.springbootonlineshopping.repository.RoleRepository;
import org.rahmi.springbootonlineshopping.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImp implements IRoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findRoleByName(String roleName) {

        return roleRepository.findByName(roleName);

    }
}
