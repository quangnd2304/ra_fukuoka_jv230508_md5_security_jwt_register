package ra.springsecurity_jwt.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.springsecurity_jwt.dto.request.RegisterRequest;
import ra.springsecurity_jwt.dto.response.RegisterResponse;
import ra.springsecurity_jwt.mapper.MapperRegister;
import ra.springsecurity_jwt.model.Users;
import ra.springsecurity_jwt.repository.UsersRepository;
import ra.springsecurity_jwt.service.UsersService;

@Service
public class UsersServiceImp implements UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private MapperRegister mapperRegister;

    @Override
    public Users findByUserNameAndStatus(String userName, boolean status) {
        return usersRepository.findByUserNameAndStatus(userName, status);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return usersRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

    @Override
    public RegisterResponse saveOrUpdate(RegisterRequest registerRequest) {
        return mapperRegister.mapperEntityToResponse(usersRepository.save(mapperRegister
                .mapperRequestToEntity(registerRequest)));
    }


}
