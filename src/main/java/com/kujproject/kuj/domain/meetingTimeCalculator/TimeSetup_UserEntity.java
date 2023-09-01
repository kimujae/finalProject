package com.kujproject.kuj.domain.meetingTimeCalculator;

import com.kujproject.kuj.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(builderMethodName = "builder")
public class TimeSetup_UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeSetupUserId;
    private int invitedUsersCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "time_setup_id")
    private TimeSetupEntity timeSetup;

    public static TimeSetup_UserEntity convertedBy(int invitedUsersCount, UserEntity user, TimeSetupEntity timeSetup) {
        return TimeSetup_UserEntity.builder()
                .user(user)
                .timeSetup(timeSetup)
                .invitedUsersCount(invitedUsersCount)
                .build();
    }
}
