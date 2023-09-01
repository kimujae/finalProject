package com.kujproject.kuj.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InvitedUserListDto {
    List<String> users;
}
