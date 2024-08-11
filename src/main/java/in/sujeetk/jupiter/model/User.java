package in.sujeetk.jupiter.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@Document("user")
public class User implements UserDetails {
    @Id
    String userId;
    String password;
    String fName;
    String lName;
    String email;
    List<SimpleGrantedAuthority> authorities;
    boolean isMfaEnabled;
    boolean isUserLocked;
    boolean isUserExpired;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isUserExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isUserLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isUserExpired;
    }

    @Override
    public boolean isEnabled() {
        return !isUserLocked;
    }
}
