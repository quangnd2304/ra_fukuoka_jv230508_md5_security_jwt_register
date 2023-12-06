package ra.springsecurity_jwt.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.springsecurity_jwt.model.ERoles;
import ra.springsecurity_jwt.model.Roles;
import ra.springsecurity_jwt.repository.RolesRepository;
import ra.springsecurity_jwt.service.RolesService;

import java.util.Optional;

@Service
public class RolesServiceImp implements RolesService {
    @Autowired
    private RolesRepository rolesRepository;
    @Override
    public Optional<Roles> findByName(ERoles name) {
        return rolesRepository.findByName(name);
    }
}
