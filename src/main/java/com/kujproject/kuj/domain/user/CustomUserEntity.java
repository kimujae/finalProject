package com.kujproject.kuj.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class CustomUserEntity {
    @Id
    String userId;
    String userName;
}
