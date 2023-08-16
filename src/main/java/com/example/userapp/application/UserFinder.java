package com.example.userapp.application;

import com.example.userapp.api.NationalIdNumber;
import com.example.userapp.commons.NotFoundException;
import com.example.userapp.domain.User;
import com.example.userapp.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserFinder {

    private final UserRepository userRepository;

    public User findByCriteria(NationalIdNumber nationalIdNumber) {
        return userRepository.findFirstByNationalIdNumber(nationalIdNumber).orElseThrow(NotFoundException::new);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}