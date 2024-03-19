package org.ponte.controller;

import lombok.extern.slf4j.Slf4j;
import org.ponte.dto.AppUserCreateCommand;
import org.ponte.dto.AppUserInfo;
import org.ponte.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<AppUserInfo> createAppUser(@Valid @RequestBody AppUserCreateCommand command) {
        log.info("Http request, POST / /api/users/createUser");
        AppUserInfo appUserInfo = appUserService.createAppUser(command);
        return new ResponseEntity<>(appUserInfo, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUserById/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long id) {
        log.info("Http request, DELETE / /api/users/deleteUserById/{userId} with variable: " + id);
        appUserService.deleteAppUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/logicalDeleteForUser/{userId}")
    public ResponseEntity<Void> logicalDeleteForUser(@PathVariable("userId") Long id) {
        log.info("Http request, PUT / /api/users/logicalDeleteForUser/{userId} with variable: " + id);
        appUserService.logicalDelete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
