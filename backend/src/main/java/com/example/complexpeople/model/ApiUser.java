package com.example.complexpeople.model;//package com.example.schoolAccess.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//@Entity
//@Table(name = "ApiUsers")
//@NoArgsConstructor
//public class ApiUser implements UserDetails {
//    @Id
//    private String username;
//    private String password;
//    private boolean enabled;
//
//
//    public ApiUser(String username, String password, boolean enabled) {
//        this.username = username;
//        this.password = password;
//        this.enabled = enabled;
//    }
//
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
//
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(() -> "read");
//    }
//
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
