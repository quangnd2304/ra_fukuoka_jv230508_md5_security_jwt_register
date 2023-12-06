package ra.springsecurity_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.springsecurity_jwt.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByUserNameAndStatus(String userName,boolean status);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
