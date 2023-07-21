package com.sparta.dtogram.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoDto {
    Long id;
    boolean isAdmin;
}
