package com.example.userapp.application;

import com.example.userapp.api.UserPatchDto;
import com.example.userapp.domain.User;
import com.example.userapp.domain.UserPatch;
import com.example.userapp.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User create(SaveUserDto dto) {
        return userRepository.save(User.create(
                dto.getPersonName(),
                dto.getNationalIdNumber().orElse(null)
        ));
    }

    public User patch(UUID uuid, UserPatchDto patchDto) {
        final User user = userRepository.getByUuid(uuid);
        UserPatch patch = new UserPatch(
                patchDto.getAddress(),
                patchDto.getRegisteredAddress(),
                patchDto.getEmail(),
                patchDto.getMobilePhone(),
                patchDto.getWorkPhone()
        );
        return user.patch(patch);
    }

    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
