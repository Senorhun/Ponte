package org.ponte.service;

import org.modelmapper.ModelMapper;
import org.ponte.domain.AppUser;
import org.ponte.domain.Contact;
import org.ponte.dto.AppUserCreateCommand;
import org.ponte.dto.AppUserInfo;
import org.ponte.dto.AppUserListInfo;
import org.ponte.dto.ContactListInfo;
import org.ponte.exceptionHandling.AppUserNotFoundException;
import org.ponte.exceptionHandling.DuplicateEmailException;
import org.ponte.exceptionHandling.UserEmailNotFoundException;
import org.ponte.repository.AppUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppUserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AppUserRepository appUserRepository;

    public AppUserService(PasswordEncoder passwordEncoder, ModelMapper modelMapper, AppUserRepository appUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.appUserRepository = appUserRepository;
    }

    public AppUserInfo createAppUser(AppUserCreateCommand command) {
        PasswordValidator.validatePassword(command.getPassword());

        if (appUserRepository.existsByEmail(command.getEmail())) {
            throw new DuplicateEmailException(command.getEmail());
        }
        AppUser appUser = new AppUser();
        appUser.setFirstName(command.getFirstName());
        appUser.setLastName(command.getLastName());
        appUser.setPassword(passwordEncoder.encode(command.getPassword()));

        appUser.setCreationDate(LocalDateTime.now());
        appUser.setEmail(command.getEmail());
        //appUser.setPassword(command.getPassword()); //B-crypt!!
        appUserRepository.save(appUser);
        return modelMapper.map(appUser, AppUserInfo.class);
    }

    public AppUser findAppUserById(Long appUserId) {
        Optional<AppUser> appUserOptional = appUserRepository.findById(appUserId);
        if (appUserOptional.isEmpty()) {
            throw new AppUserNotFoundException(appUserId);
        }
        return appUserOptional.get();
    }

    public void deleteAppUser(Long id) {
        AppUser appUser = findAppUserById(id);
        appUserRepository.delete(appUser);
    }

    public void logicalDelete(Long id) {
        AppUser appUser = findAppUserById(id);
        appUser.setFirstName(null);
        appUser.setLastName(null);
        appUserRepository.save(appUser);
    }
    public AppUser findUserByEmail(String email) {
        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(email);
        if (optionalAppUser.isEmpty()) {
            throw new UserEmailNotFoundException(email);
        }
        return optionalAppUser.get();
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     //   System.out.println("email" + email);
        AppUser appUser = findUserByEmail(email);

        String[] roles = appUser.getRoles().stream()
                .map(Enum::toString)
                .toArray(String[]::new);

        return User
                .withUsername(appUser.getEmail())
                .authorities(AuthorityUtils.createAuthorityList(roles))
                .password(appUser.getPassword())
                .build();
    }

    public List<AppUserListInfo> findAllAppUsers(int pageNo, int pageSize) {
            PageRequest pageable = PageRequest.of(pageNo, pageSize);
            Page<AppUser> contactPage = appUserRepository.findAll(pageable);

            List<AppUserListInfo> appUserListInfos = contactPage.getContent()
                    .stream()
                    .map(appUser -> modelMapper.map(appUser, AppUserListInfo.class))
                    .collect(Collectors.toList());
            return appUserListInfos;
    }
}
