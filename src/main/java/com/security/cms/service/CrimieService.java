package com.security.cms.service;


import com.security.cms.exceptions.ResourceNotFoundException;
import com.security.cms.interfaces.CrimeInterface;
import com.security.cms.models.Crime;
import com.security.cms.models.Criminal;
import com.security.cms.repos.CrimeRepository;
import com.security.cms.repos.CriminalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CrimieService implements CrimeInterface {

    private final CrimeRepository crimeRepository;
    private final CriminalRepository criminalRepository;

    public CrimieService(CrimeRepository crimeRepository, CriminalRepository criminalRepository) {
        this.crimeRepository = crimeRepository;
        this.criminalRepository = criminalRepository;
    }

    @Override
    public List<Crime> ciminalCrimes(Long id) {
        Optional<Criminal> isCriminalExisted=criminalRepository.
                findById(id);
        if(isCriminalExisted.isPresent()){
            return crimeRepository.getCrimesByCriminalId(id);
        }
        throw new ResourceNotFoundException("Criminal record does not found");

    }

    @Override
    public ResponseEntity<?> addCrime(Crime crime, Long id) {
        Criminal criminal=criminalRepository.getCriminalById(id);
        if(criminal!=null){
            crime.setCriminal(criminal);
            crimeRepository.save(crime);
            return ResponseEntity.created(URI.create("/crime"+id)).body("Crime details uploaded successfully.");
        }
        return ResponseEntity.badRequest().body("Criminal not found");
    }

    @Override
    public ResponseEntity<?> deleteCrime(Long id) {
        Optional<Crime> crime=crimeRepository.findById(id);
        if(crime.isEmpty()){
            throw new ResourceNotFoundException("Crime record not found");
        }
        crimeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
