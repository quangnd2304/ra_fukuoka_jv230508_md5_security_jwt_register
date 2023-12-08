package ra.springsecurity_jwt.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ra.springsecurity_jwt.dto.request.LoginRequest;
import ra.springsecurity_jwt.dto.request.RegisterRequest;
import ra.springsecurity_jwt.dto.response.LoginResponse;
import ra.springsecurity_jwt.dto.response.RegisterResponse;
import ra.springsecurity_jwt.jwt.JwtTokenProvider;
import ra.springsecurity_jwt.mapper.MapperRegister;
import ra.springsecurity_jwt.model.Users;
import ra.springsecurity_jwt.repository.UsersRepository;
import ra.springsecurity_jwt.security.CustomUserDetail;
import ra.springsecurity_jwt.service.UsersService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImp implements UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private MapperRegister mapperRegister;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
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

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        //Sinh JWT tra ve client
        String jwt = jwtTokenProvider.generateToken(customUserDetail);
        //Lay cac quyen cua user
        List<String> listRoles = customUserDetail.getAuthorities().stream()
                .map(item->item.getAuthority()).collect(Collectors.toList());
        return new LoginResponse(jwt,"Bearer",customUserDetail.getUsername(),
                customUserDetail.getEmail(),customUserDetail.getPhone(),listRoles);
    }
}
