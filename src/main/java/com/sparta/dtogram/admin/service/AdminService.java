package com.sparta.dtogram.admin.service;

import com.sparta.dtogram.user.dto.ProfileRequestDto;
import com.sparta.dtogram.user.entity.User;
import com.sparta.dtogram.user.entity.UserRoleEnum;
import com.sparta.dtogram.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    @Transactional
    public void editProfileByAdmin(User userAdmin, Long targetId, ProfileRequestDto requestDto) {
        checkAdminRole(userAdmin);
        findUser(targetId)
                .updateProfile(requestDto); //todo 기존 profile업데이트 기능을 계속 써도 될지?
    }

    @Transactional
    public void editRoleByAdmin(User userAdmin, Long targetId, String role) {
        checkAdminRole(userAdmin);

        UserRoleEnum newRole = UserRoleEnum.valueOf(role);

        findUser(targetId)
                .updateRole(newRole);

    }

    public void deleteUserByAdmin(User userAdmin, Long targetId) {
        checkAdminRole(userAdmin);
        userRepository.delete(findUser(targetId));
    }





    private void checkAdminRole(User userAdmin) {
        if (userAdmin.getRole() != UserRoleEnum.ADMIN) {
            throw new IllegalArgumentException("Admin 권한이 없습니다.");
        }
    }

    private User findUser(Long targetId) {
        User targetUser = userRepository.findById(targetId).orElseThrow(() ->
                new IllegalArgumentException("해당 유저의 정보를 찾을 수 없습니다.")
        );
        return targetUser;
    }
}
