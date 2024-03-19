package org.ponte.controller;

import lombok.extern.slf4j.Slf4j;
import org.ponte.dto.ContactCreateCommand;
import org.ponte.dto.ContactInfo;
import org.ponte.dto.ContactLocationCreateCommand;
import org.ponte.dto.ContactLocationInfo;
import org.ponte.service.ContactLocationService;
import org.ponte.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/contacts")
@Slf4j
public class ContactController {
    private final ContactService contactService;
    private final ContactLocationService contactLocationService;

    public ContactController(ContactService contactService, ContactLocationService contactLocationService) {
        this.contactService = contactService;
        this.contactLocationService = contactLocationService;
    }

    @PostMapping("/createContactForUser")
    public ResponseEntity<ContactInfo> createContactForUser(@Valid @RequestBody ContactCreateCommand command){
        log.info("Http request, POST / /api/users/createContactForUser");
        ContactInfo contactInfo = contactService.createContactForUser(command);
        return new ResponseEntity<>(contactInfo, HttpStatus.CREATED);
    }

    @PostMapping("/createContactLocationForContact")
    public ResponseEntity<ContactLocationInfo> createContactLocationForContact(@Valid @RequestBody ContactLocationCreateCommand command){
        log.info("Http request, POST / /api/users/createContactForUser");
        ContactLocationInfo contactLocationInfo = contactLocationService.createContactLocationForContact(command);
        return new ResponseEntity<>(contactLocationInfo, HttpStatus.CREATED);
    }
}
