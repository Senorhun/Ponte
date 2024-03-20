package org.ponte.controller;

import lombok.extern.slf4j.Slf4j;
import org.ponte.dto.AppUserCreateCommand;
import org.ponte.dto.AppUserInfo;
import org.ponte.dto.AppUserListInfo;
import org.ponte.dto.AppUserUpdateCommand;
import org.ponte.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/me")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<UserDetails> getLoggedInUser() {
        log.info("Http request, GET / /api/users/me");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails loggedInUser = (User) authentication.getPrincipal();
        return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("Http request, GET / /api/users/logout");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }

    @PostMapping("/createUser")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<AppUserInfo> createAppUser(@Valid @RequestBody AppUserCreateCommand command) {
        log.info("Http request, POST / /api/users/createUser with command: " + command.toString());
        AppUserInfo appUserInfo = appUserService.createAppUser(command);
        return new ResponseEntity<>(appUserInfo, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUserById/{userId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long id) {
        log.info("Http request, DELETE / /api/users/deleteUserById/{userId} with variable: " + id);
        appUserService.deleteAppUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateAppUser/{appuserId}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<AppUserInfo> updateAppUserById(@PathVariable("appuserId") Long id, @Valid @RequestBody AppUserUpdateCommand command) {
        log.info("Http request, PUT /api/users/updateAppUser/{appuserId} body: " + command.toString() + " with variable: " + id);
        AppUserInfo appUserInfo = appUserService.updateAppUserById(id, command);
        return new ResponseEntity<>(appUserInfo, HttpStatus.ACCEPTED);
    }

    @PutMapping("/logicalDeleteForUser/{userId}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Void> logicalDeleteForUser(@PathVariable("userId") Long id) {
        log.info("Http request, PUT / /api/users/logicalDeleteForUser/{userId} with variable: " + id);
        appUserService.logicalDelete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findAllAppUsers")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<AppUserListInfo>> findAllAppUsers(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        log.info("Http request, GET / /api/users/findAllAppUsers");
        List<AppUserListInfo> appUserListInfos = appUserService.findAllAppUsers(pageNo, pageSize);
        return new ResponseEntity<>(appUserListInfos, HttpStatus.FOUND);
    }

    @GetMapping("/findUserById/{userId}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<AppUserInfo> findUserById(@PathVariable("userId") Long id) {
        log.info("Http request, GET / /api/users/findUserById/{userId} with variable: " + id);
        AppUserInfo appUserInfo = appUserService.getUserById(id);
        return new ResponseEntity<>(appUserInfo, HttpStatus.FOUND);
    }
}
