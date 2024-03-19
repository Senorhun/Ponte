package org.ponte.service;

import org.modelmapper.ModelMapper;
import org.ponte.domain.AppUser;
import org.ponte.dto.AppUserCreateCommand;
import org.ponte.dto.AppUserInfo;
import org.ponte.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class AppUserService {

    private final ModelMapper modelMapper;
    private final AppUserRepository appUserRepository;

    public AppUserService(ModelMapper modelMapper, AppUserRepository appUserRepository) {
        this.modelMapper = modelMapper;
        this.appUserRepository = appUserRepository;
    }

    public AppUserInfo createAppUser(AppUserCreateCommand command) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(command.getFirstName());
        appUser.setLastName(command.getLastName());

        appUser.setCreationDate(LocalDateTime.now());
        appUser.setEmail(command.getEmail());
        appUserRepository.save(appUser);
        return modelMapper.map(appUser,AppUserInfo.class);
    }
}
