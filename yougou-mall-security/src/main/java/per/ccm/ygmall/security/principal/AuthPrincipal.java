package per.ccm.ygmall.security.principal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AuthPrincipal implements UserDetails {

    /**
     * 认证授权ID
     * */
    private Long authAccountId;

    /**
     * 用户ID
     * */
    private Long userId;

    /**
     * 用户名称
     * */
    private String username;

    /**
     * 密码
     * */
    private String password;

    /**
     * 授权范围
     * */
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
