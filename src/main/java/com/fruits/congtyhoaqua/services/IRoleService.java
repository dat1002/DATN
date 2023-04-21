package com.fruits.congtyhoaqua.services;


import com.fruits.congtyhoaqua.dtos.RoleDTO;
import com.fruits.congtyhoaqua.models.Role;

import java.util.Set;

public interface IRoleService {
    Role createRole(RoleDTO roleDTO);
    Set<Role> getAllRole();
    Role editRole(Integer idRole,RoleDTO roleDTO);
    Role deleteRole(Integer idRole);
}
