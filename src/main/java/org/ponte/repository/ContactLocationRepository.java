package org.ponte.repository;

import org.ponte.domain.ContactLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactLocationRepository extends JpaRepository<ContactLocation, Long> {
}
