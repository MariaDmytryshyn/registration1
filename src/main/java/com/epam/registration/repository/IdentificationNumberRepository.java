package com.epam.registration.repository;

import com.epam.registration.model.IdentificationNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentificationNumberRepository extends JpaRepository<IdentificationNumber, Long> {
}
