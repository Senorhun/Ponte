package org.ponte.service;

import org.modelmapper.ModelMapper;
import org.ponte.domain.Contact;
import org.ponte.domain.ContactLocation;
import org.ponte.dto.ContactLocationCreateCommand;
import org.ponte.dto.ContactLocationInfo;
import org.ponte.repository.ContactLocationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ContactLocationService {

    private final ModelMapper modelMapper;
    private final ContactLocationRepository contactLocationRepository;
    private final ContactService contactService;

    public ContactLocationService(ModelMapper modelMapper, ContactLocationRepository contactLocationRepository, ContactService contactService) {
        this.modelMapper = modelMapper;
        this.contactLocationRepository = contactLocationRepository;
        this.contactService = contactService;
    }
    public ContactLocationInfo createContactLocationForContact(ContactLocationCreateCommand command){

        ContactLocation contactLocationToSave = modelMapper.map(command, ContactLocation.class);
        Contact contact = contactService.findContactById(command.getContactId());
        contactLocationToSave.setContact(contact);
        ContactLocation savedContactLocation = contactLocationRepository.save(contactLocationToSave);
        return modelMapper.map(savedContactLocation, ContactLocationInfo.class);
    }

   /* private ContactLocation findContactLocationById(Long contactLocationId) {
        Optional<ContactLocation> contactLocationOptional = contactLocationRepository.findById(contactLocationId);
        if(contactLocationOptional.isEmpty()){
            // throw new ContactLocationNotFoundException(appUserId);
        }
        return contactLocationOptional.get();
    }

    */
}
