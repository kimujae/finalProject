package com.kujproject.kuj.authentication;

import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.domain.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    시큐리티 설정에서 loginProcessingUrl 설정을 보고 요청이 파싱되면,
    자동으로 UserDetailsService 타입으로 Ioc되어있는 loadUserByUsername 메소드 실행
 */
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //UserDetails userDetails = new PrincipalDetails();
        Optional<UserEntity> userEntity = userDao.findById(username);

        if(userEntity.isPresent()) {
            UserEntity user = userEntity.get();

            PrincipalDetails principalDetails = new PrincipalDetails();
            principalDetails.setUsername(user.getUserId());
            principalDetails.setPassword(user.getPassword());


            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));


            //principalDetails객체 권한 설정 , 추후 예외 처리
            principalDetails.setAuthorities(authorities);
            principalDetails.setEnabled(true);
            principalDetails.setAccountNonExpired(true);
            principalDetails.setAccountNonLocked(true);
            principalDetails.setCredentilasNonExpired(true);

            System.out.println(user.getRole());
            System.out.println(principalDetails.getAuthorities());
            return principalDetails;
        }
        return null;
    }
}
