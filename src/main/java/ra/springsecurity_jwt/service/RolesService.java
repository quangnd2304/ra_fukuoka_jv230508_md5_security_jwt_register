package ra.springsecurity_jwt.service;

import ra.springsecurity_jwt.model.ERoles;
import ra.springsecurity_jwt.model.Roles;

import java.util.Optional;

public interface RolesService {
    Optional<Roles> findByName(ERoles name);
}
