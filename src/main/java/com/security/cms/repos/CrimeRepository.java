package com.security.cms.repos;

import com.security.cms.models.Crime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrimeRepository extends JpaRepository<Crime,Long> {
    List<Crime> getCrimesByCriminalId(Long id);


}
