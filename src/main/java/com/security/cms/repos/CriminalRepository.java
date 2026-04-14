package com.security.cms.repos;

import com.security.cms.models.Criminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CriminalRepository extends JpaRepository<Criminal,Long> {
    Optional<Criminal> getCriminalByName(String name);
    Criminal getCriminalById(Long id);
}
