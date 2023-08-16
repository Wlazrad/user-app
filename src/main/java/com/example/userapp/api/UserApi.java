package com.example.userapp.api;

import com.example.userapp.application.UserFinder;
import com.example.userapp.application.UserService;
import com.example.userapp.domain.User;
import com.example.userapp.service.CsvExportService;
import com.example.userapp.service.UserGenerationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

@RequestMapping("/api/int/user")
@RestController
@RequiredArgsConstructor
@Validated
public class UserApi {
    private final UserFinder userFinder;
    private final UserService userService;
    private final UserGenerationService userGenerationService;
    private final CsvExportService csvExportService;


    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Użytkownik został utworzony"),
            @ApiResponse(responseCode = "400", description = "Nieprawidłowa data urodzenia lub numer PESEL"),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserOutput create(
            @Parameter(description = "Dane wejściowe", required = true)
            @RequestBody @Valid UserInput input
    ) {
        return UserOutput.from(userService.create(
                input.toDto()
        ));
    }

    @Operation(summary = "Wyszukiwanie użytkowników")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista użytkowników"),
            @ApiResponse(responseCode = "400", description = "System i wartość identyfikatora nie zostały przekazane razem")
    })
    @GetMapping
    public Stream<UserOutput> find() {
        return userFinder.findAll().stream()
                .map(UserOutput::from);
    }

    @Operation(summary = "Wyszukiwanie użytkowników")
    @ApiResponses({
            @ApiResponse(
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserOutput.class))),
                    description = "Lista użytkowników"
            ),
            @ApiResponse(responseCode = "400", description = "Nieprawidłowe dane wejściowe")
    })
    @PostMapping("/searches")
    public UserOutput search(@RequestBody NationalIdNumberInput nationalIdNumberRequest) {
        return UserOutput.from(userFinder.findByCriteria(nationalIdNumberRequest.toNationalIdNumber()));
    }

    @Operation(summary = "Dodanie metody komunikacji użytkownika")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dodano metody komunikacji"),
            @ApiResponse(responseCode = "403", description = "Brak dostępu do aktualizacji Użytkownika"),
            @ApiResponse(responseCode = "404", description = "Użytkownik nie został znaleziony"),
    })
    @PatchMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public UserOutput patch(
            @Parameter(description = "Globalny identyfikator Użytkownika", required = true)
            @PathVariable UUID uuid,
            @Parameter(description = "Dane wejściowe", required = true)
            @RequestBody @Valid UserPartialInput input
    ) {
        return UserOutput.from(userService.patch(
                uuid,
                input.toDto()
        ));
    }

    @GetMapping("/view")
    public ModelAndView showAllUsers(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "size", required = false) Integer size) {
        int currentPage = page != null ? page : 0;
        int pageSize = size != null ? size : 10;

        PageRequest pageable = PageRequest.of(currentPage, pageSize);
        Page<User> userPage = userService.findAllUsers(pageable);

        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", userPage.getContent());
        modelAndView.addObject("totalPages", userPage.getTotalPages());
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.addObject("size", pageSize);
        return modelAndView;
    }

    @Operation(summary = "Export pliku CSV")
    @GetMapping("/export")
    public ResponseEntity<Resource> exportUsersToCSV() throws IOException {
        ByteArrayResource resource = csvExportService.generateCsvFileResource();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=users.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }

    @Operation(summary = "Generowanie 15000 użytkowników")
    @GetMapping("/generate")
    public ResponseEntity<String> generateUsers() {
        userGenerationService.generateAndSaveRandomUsers(15000);
        return ResponseEntity.ok("15,000 Users have been successfully generated.");
    }
}
