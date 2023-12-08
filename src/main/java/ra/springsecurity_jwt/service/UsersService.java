package ra.springsecurity_jwt.service;

import ra.springsecurity_jwt.dto.request.LoginRequest;
import ra.springsecurity_jwt.dto.request.RegisterRequest;
import ra.springsecurity_jwt.dto.response.LoginResponse;
import ra.springsecurity_jwt.dto.response.RegisterResponse;
import ra.springsecurity_jwt.model.Users;

public interface UsersService {
    Users findByUserNameAndStatus(String userName, boolean status);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    RegisterResponse saveOrUpdate(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
