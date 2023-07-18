package com.kujproject.kuj.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/*
    시큐리티 설정에서 loginProcessingUrl 설정을 보고 요청이 파싱되면,
    자동으로 UserDetailsService 타입으로 Ioc되어있는 loadUserByUsername 메소드 실행
 */
@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //UserDetails userDetails = new PrincipalDetails();
        return null;
    }
}
