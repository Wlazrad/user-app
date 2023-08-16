package com.example.userapp.service;

import com.example.userapp.application.UserService;
import com.example.userapp.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class CsvExportServiceTest {

    @InjectMocks
    private CsvExportService csvExportService;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateCsvFileResource() throws IOException {
        // Given
        User user = UserStub.createUserStub();
        List<User> users = Arrays.asList(user);
        when(userService.getAllUsers()).thenReturn(users);

        // When
        ByteArrayResource resource = csvExportService.generateCsvFileResource();

        // Then
        String csvContent = new String(resource.getByteArray(), StandardCharsets.UTF_8);
        assertTrue(csvContent.contains(user.getUuid().toString()));
        assertTrue(csvContent.contains(user.getPersonName().getFirstName()));

    }
}

