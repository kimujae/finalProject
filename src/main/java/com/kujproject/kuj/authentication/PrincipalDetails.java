package com.kujproject.kuj.authentication;

import com.kujproject.kuj.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user;
    public PrincipalDetails(User user) {
        this.user = user;
    }



    // 해당 유저의 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // return type이 string 이므로, Collection<GrantedAuthority>에 넣어서 반환해 줌
        Collection<GrantedAuthority> grantedAuthoritieslist = new ArrayList<>();
        grantedAuthoritieslist.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return grantedAuthoritieslist;
    }

    @Override
    public String getPassword() {
        return user.getPassWord();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    //===============       [추후 정의]       =================
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
