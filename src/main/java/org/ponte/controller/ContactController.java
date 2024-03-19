package org.ponte.controller;

import lombok.extern.slf4j.Slf4j;
import org.ponte.domain.ContactLocation;
import org.ponte.dto.ContactCreateCommand;
import org.ponte.dto.ContactInfo;
import org.ponte.dto.ContactLocationCreateCommand;
import org.ponte.dto.ContactLocationInfo;
import org.ponte.service.ContactLocationService;
import org.ponte.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

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

    @DeleteMapping("/deleteContactById/{contactId}")
    public ResponseEntity<Void> deleteContact(@PathVariable("contactId") Long id) {
        log.info("Http request, DELETE / /api/users/deleteContact/{contactId} with variable: " + id);
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/createContactLocationForContact")
    public ResponseEntity<ContactLocationInfo> createContactLocationForContact(@Valid @RequestBody ContactLocationCreateCommand command){
        log.info("Http request, POST / /api/users/createContactLocationForContact");
        ContactLocationInfo contactLocationInfo = contactLocationService.createContactLocationForContact(command);
        return new ResponseEntity<>(contactLocationInfo, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteContactLocationById/{contactLocationId}")
    public ResponseEntity<Void> deleteContactLocation(@PathVariable("contactLocationId") Long id) {
        log.info("Http request, DELETE / /api/users/deleteContactLocation/{contactLocationId} with variable: " + id);
        contactLocationService.deleteContactLocation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
