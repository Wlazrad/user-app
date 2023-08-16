package com.example.userapp.service;

import com.example.userapp.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

class UserGenerationServiceTest {

    @InjectMocks
    private UserGenerationService userGenerationService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateAndSaveRandomUsers_UniqueUUIDs() {
        // Given
        int count = 5;

        // When
        userGenerationService.generateAndSaveRandomUsers(count);

        // Then
        verify(userRepository, times(1)).saveAll(argThat(users -> {
            Set<String> uniqueUUIDs = new HashSet<>();
            users.forEach(user -> uniqueUUIDs.add(user.getUuid().toString()));
            return uniqueUUIDs.size() == count;
        }));
    }
}
