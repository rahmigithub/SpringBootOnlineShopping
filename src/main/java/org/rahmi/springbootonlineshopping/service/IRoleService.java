package org.rahmi.springbootonlineshopping.service;

import org.rahmi.springbootonlineshopping.model.Role;

import java.util.Optional;

public interface IRoleService {

    Optional<Role> findRoleByName(String roleName);
}
