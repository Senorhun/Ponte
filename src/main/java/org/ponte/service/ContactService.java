package org.ponte.service;

import org.modelmapper.ModelMapper;
import org.ponte.domain.AppUser;
import org.ponte.domain.Contact;
import org.ponte.domain.ContactLocation;
import org.ponte.dto.ContactCreateCommand;
import org.ponte.dto.ContactInfo;
import org.ponte.dto.ContactListInfo;
import org.ponte.dto.ContactLocationListInfo;
import org.ponte.exceptionHandling.ContactNotFoundException;
import org.ponte.repository.ContactLocationRepository;
import org.ponte.repository.ContactRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContactService {

    private final ModelMapper modelMapper;
    private final ContactRepository contactRepository;
    private final AppUserService appUserService;
    private final ContactLocationRepository contactLocationRepository;


    public ContactService(ModelMapper modelMapper, ContactRepository contactRepository, AppUserService appUserService, ContactLocationRepository contactLocationRepository) {
        this.modelMapper = modelMapper;
        this.contactRepository = contactRepository;
        this.appUserService = appUserService;
        this.contactLocationRepository = contactLocationRepository;
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
        if (appUserOptional.isEmpty()) {
            throw new ContactNotFoundException(contactId);
        }
        return appUserOptional.get();
    }

    public void deleteContact(Long id) {
        Contact contact = findContactById(id);
        contactRepository.delete(contact);
    }

    public List<ContactListInfo> findAllContacts(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Contact> contactPage = contactRepository.findAll(pageable);

        List<ContactListInfo> contactsListInfos = contactPage.getContent()
                .stream()
                .map(contact -> modelMapper.map(contact, ContactListInfo.class))
                .collect(Collectors.toList());
        return contactsListInfos;
    }

    public List<ContactLocationListInfo> findAllContactLocations(int pageNo, int pageSize) {

        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<ContactLocation> contactLocationPage = contactLocationRepository.findAll(pageable);

        List<ContactLocationListInfo> contactLocationListInfos = contactLocationPage.getContent()
                .stream()
                .map(contactLocation -> modelMapper.map(contactLocation, ContactLocationListInfo.class))
                .collect(Collectors.toList());
        return contactLocationListInfos;
    }
}
