package com.kujproject.kuj.authentication;

import com.kujproject.kuj.dto.user.AuthDto;
import com.kujproject.kuj.dto.user.UserRespDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Slf4j
@Getter
@AllArgsConstructor
public class PrincipalDetails implements UserDetails {

    @Delegate
    private AuthDto userDto;
//    private boolean isEnabled;
//    private boolean isAccountNonExpired;
//    private boolean isAccountNonLocked;
//    private boolean isCredentilasNonExpired;
    private Collection<? extends GrantedAuthority> authorities;

    private PrincipalDetails(UserRespDto userRespDto) {}


    // 해당 유저의 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userDto.getUserPassword();
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

    @Override
    public String getUsername() {
        return userDto.getUserName();
    }

//    public void setUsername(String username) {
//        this.username = username;
//    }

    //===============       [추후 정의]       =================
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
