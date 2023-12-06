package ra.springsecurity_jwt.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ra.springsecurity_jwt.model.Users;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CustomUserDetail implements UserDetails {
    private long id;
    private String userName;
    @JsonIgnore
    private String password;
    private String email;
    private String phone;
    private boolean status;
    private Collection<? extends GrantedAuthority> listAuthorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.listAuthorities;
    }

    //Tu thong tin user chuyen sang thong tin CustomUserDetails
    public static CustomUserDetail mapUserToUserDetail(Users user) {
        //Lay cac quyen tu doi tuong user
        List<GrantedAuthority> listAuthorities = user.getListRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getName().name()))
                .collect(Collectors.toList());
        //Tra ve doi tuong CustomUserDetails
        return new CustomUserDetail(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getPhone(),
                user.isStatus(),
                listAuthorities
        );

    }


    @Override
    public String getPassword() {
        return this.password;

    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
