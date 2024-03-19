package org.ponte.service;

import org.modelmapper.ModelMapper;
import org.ponte.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AppUserService {

    private final ModelMapper modelMapper;
    private final AppUserRepository appUserRepository;

    public AppUserService(ModelMapper modelMapper, AppUserRepository appUserRepository) {
        this.modelMapper = modelMapper;
        this.appUserRepository = appUserRepository;
    }
}
