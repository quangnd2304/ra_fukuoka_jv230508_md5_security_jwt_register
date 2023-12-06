package ra.springsecurity_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.springsecurity_jwt.model.ERoles;
import ra.springsecurity_jwt.model.Roles;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
    Optional<Roles> findByName(ERoles name);
}
