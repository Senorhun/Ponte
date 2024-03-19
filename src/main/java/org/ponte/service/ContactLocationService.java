package org.ponte.service;

import org.modelmapper.ModelMapper;
import org.ponte.domain.Contact;
import org.ponte.domain.ContactLocation;
import org.ponte.dto.ContactLocationCreateCommand;
import org.ponte.dto.ContactLocationInfo;
import org.ponte.exceptionHandling.ContactLocationNotFoundException;
import org.ponte.exceptionHandling.EmailOrPhoneNotValidException;
import org.ponte.repository.ContactLocationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.regex.Pattern;

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

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public boolean isValidPhone(String phone) {
        String phoneRegex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phone).matches();
    }

    public ContactLocationInfo createContactLocationForContact(ContactLocationCreateCommand command) {
        Contact contact = contactService.findContactById(command.getContactId());

        ContactLocation contactLocation = new ContactLocation();
        contactLocation.setContact(contact);
        contactLocation.setCity(command.getCity());
        contactLocation.setStreet(command.getStreet());
        contactLocation.setHouseNumber(command.getHouseNumber());
        contactLocation.setPostalCode(command.getPostalCode());

        if (!command.getEmail().isEmpty() && isValidEmail(command.getEmail())) {
            contactLocation.setEmail(command.getEmail());
            if (!command.getPhone().isEmpty() && isValidPhone(command.getPhone())) {
                contactLocation.setPhone(command.getPhone());
            }
        } else if (!command.getPhone().isEmpty() && isValidPhone(command.getPhone())) {
            contactLocation.setPhone(command.getPhone());
        } else {
            throw new EmailOrPhoneNotValidException();
        }
        ContactLocation savedContactLocation = contactLocationRepository.save(contactLocation);
        return modelMapper.map(savedContactLocation, ContactLocationInfo.class);
    }

    public void deleteContactLocation(Long id) {
        ContactLocation contactLocation = findContactLocationById(id);
        contactLocationRepository.delete(contactLocation);
    }

    private ContactLocation findContactLocationById(Long contactLocationId) {
        Optional<ContactLocation> contactLocationOptional = contactLocationRepository.findById(contactLocationId);
        if (contactLocationOptional.isEmpty()) {
            throw new ContactLocationNotFoundException(contactLocationId);
        }
        return contactLocationOptional.get();
    }

}
