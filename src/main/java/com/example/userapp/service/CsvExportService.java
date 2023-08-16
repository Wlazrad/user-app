package com.example.userapp.service;


import com.example.userapp.api.NationalIdNumber;
import com.example.userapp.application.UserService;
import com.example.userapp.domain.Address;
import com.example.userapp.domain.RegisteredAddress;
import com.example.userapp.domain.User;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvExportService {

    private final UserService userService;

    public void exportUsersToCSV(List<User> users, OutputStreamWriter writer) throws IOException {
        try (CSVWriter csvWriter = new CSVWriter(writer)) {
            csvWriter.writeNext(new String[]{"UUID", "Name", "National ID", "Email", "Mobile Phone", "Work Phone", "Address", "Registered Address"});
            for (User user : users) {
                csvWriter.writeNext(new String[]{
                        user.getUuid().toString(),
                        user.getPersonName().getFirstName() + " " + user.getPersonName().getLastName(),
                        user.getNationalIdNumber().orElse(new NationalIdNumber("N/A")).toString(),
                        user.getEmail().orElse("N/A"),
                        user.getMobilePhone().orElse("N/A"),
                        user.getWorkPhone().orElse("N/A"),
                        user.getAddress().map(Address::toString).orElse("N/A"),
                        user.getRegisteredAddress().map(RegisteredAddress::toString).orElse("N/A")
                });
            }
        }
    }

    public ByteArrayResource generateCsvFileResource() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (OutputStreamWriter writer = new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8)) {
            exportUsersToCSV(userService.getAllUsers(), writer);
        }
        return new ByteArrayResource(byteArrayOutputStream.toByteArray());
    }
}
