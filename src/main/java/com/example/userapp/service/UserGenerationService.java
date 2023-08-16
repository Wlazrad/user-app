package com.example.userapp.service;


import com.example.userapp.domain.User;
import com.example.userapp.domain.UserFactory;
import com.example.userapp.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserGenerationService {

    private final UserRepository userRepository;

    public void generateAndSaveRandomUsers(int count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User user = generateRandomUser(i);
            users.add(user);
        }
        userRepository.saveAll(users);
    }

    private User generateRandomUser(int index) {
        String firstName = "FirstName" + index;
        String lastName = "LastName" + index;
        String nationalId = "ID" + new Random().nextInt(999999);
        User user = UserFactory.createUser(firstName, lastName, nationalId);
        assignRandomCommunicationMethods(user);
        return user;
    }

    private void assignRandomCommunicationMethods(User user) {
        Random random = new Random();
        int methodsCount = random.nextInt(3) + 2;
        for (int i = 0; i < methodsCount; i++) {
            switch (i) {
                case 0:
                    user.assignEmail("user" + user.getUuid() + "@example.com");
                    break;
                case 1:
                    user.assignMobilePhone("+1234567890");
                    break;
                case 2:
                    user.assignWorkPhone("+0987654321");
                    break;
                default:
                    break;
            }
        }
    }
}
