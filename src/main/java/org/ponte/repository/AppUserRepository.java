package org.ponte.repository;

import org.ponte.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
