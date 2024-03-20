package org.ponte.controller;

import lombok.extern.slf4j.Slf4j;
import org.ponte.dto.*;
import org.ponte.service.ContactLocationService;
import org.ponte.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<ContactInfo> createContactForUser(@Valid @RequestBody ContactCreateCommand command) {
        log.info("Http request, POST / /api/contacts/createContactForUser");
        ContactInfo contactInfo = contactService.createContactForUser(command);
        return new ResponseEntity<>(contactInfo, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteContactById/{contactId}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Void> deleteContact(@PathVariable("contactId") Long id) {
        log.info("Http request, DELETE / /api/contacts/deleteContactById/{contactId} with variable: " + id);
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/createContactLocationForContact")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<ContactLocationInfo> createContactLocationForContact(@Valid @RequestBody ContactLocationCreateCommand command) {
        log.info("Http request, POST / /api/contacts/createContactLocationForContact with command: " + command.toString());
        ContactLocationInfo contactLocationInfo = contactLocationService.createContactLocationForContact(command);
        return new ResponseEntity<>(contactLocationInfo, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteContactLocationById/{contactLocationId}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Void> deleteContactLocation(@PathVariable("contactLocationId") Long id) {
        log.info("Http request, DELETE / /api/contacts/deleteContactLocationById/{contactLocationId} with variable: " + id);
        contactLocationService.deleteContactLocation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findAllContacts")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<ContactListInfo>> findAllContacts(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        log.info("Http request, GET / /api/contacts/findAllContacts");
        List<ContactListInfo> contactsListInfos = contactService.findAllContacts(pageNo, pageSize);
        return new ResponseEntity<>(contactsListInfos, HttpStatus.FOUND);
    }

    @GetMapping("/findAllContactLocations")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<ContactLocationListInfo>> findAllContactLocations(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        log.info("Http request, GET / /api/contacts/findAllContactLocations");
        List<ContactLocationListInfo> contactLocationListInfos = contactService.findAllContactLocations(pageNo, pageSize);
        return new ResponseEntity<>(contactLocationListInfos, HttpStatus.FOUND);
    }

    @GetMapping("/findContactById/{contactId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<ContactInfo> findContactById(@PathVariable("contactId") Long id) {
        log.info("Http request, GET / /api/contacts/findContactById/{contactId} with variable: " + id);
        ContactInfo contactInfo = contactService.getContactById(id);
        return new ResponseEntity<>(contactInfo, HttpStatus.FOUND);
    }
}
