package org.ponte.service;

import org.modelmapper.ModelMapper;
import org.ponte.domain.AppUser;
import org.ponte.domain.Contact;
import org.ponte.domain.ContactLocation;
import org.ponte.dto.ContactCreateCommand;
import org.ponte.dto.ContactInfo;
import org.ponte.dto.ContactLocationCreateCommand;
import org.ponte.dto.ContactLocationInfo;
import org.ponte.repository.ContactRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ContactService {

    private final ModelMapper modelMapper;
    private final ContactRepository contactRepository;
    private final AppUserService appUserService;



    public ContactService(ModelMapper modelMapper, ContactRepository contactRepository, AppUserService appUserService) {
        this.modelMapper = modelMapper;
        this.contactRepository = contactRepository;
        this.appUserService = appUserService;

    }


    public ContactInfo createContactForUser(ContactCreateCommand command) {

        Contact contactToSave = modelMapper.map(command, Contact.class);
        AppUser appUser = appUserService.findAppUserById(command.getAppUserId());
        contactToSave.setAppUser(appUser);
        Contact savedContact = contactRepository.save(contactToSave);
        return modelMapper.map(savedContact, ContactInfo.class);
    }


    public Contact findContactById(Long contactId) {
        Optional<Contact> appUserOptional = contactRepository.findById(contactId);
        if(appUserOptional.isEmpty()){
            // throw new ContactNotFoundException(appUserId);
        }
        return appUserOptional.get();
    }

    public void deleteContact(Long id) {
        Contact contact = findContactById(id);
        contactRepository.delete(contact);
    }
}
