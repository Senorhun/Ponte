package org.ponte.controller;

import lombok.extern.slf4j.Slf4j;
import org.ponte.dto.AppUserCreateCommand;
import org.ponte.dto.AppUserInfo;
import org.ponte.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<AppUserInfo> createAppUser(@Valid @RequestBody AppUserCreateCommand command){
        log.info("Http request, POST / /api/users/createUser");
        AppUserInfo appUserInfo = appUserService.createAppUser(command);
        return new ResponseEntity<>(appUserInfo, HttpStatus.CREATED);
    }
}
