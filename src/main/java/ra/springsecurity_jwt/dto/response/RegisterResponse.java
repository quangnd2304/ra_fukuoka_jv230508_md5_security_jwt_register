package ra.springsecurity_jwt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    private long id;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String address;
}
