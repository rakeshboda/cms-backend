package com.security.cms.service;

import com.security.cms.exceptions.ResourceNotFoundException;
import com.security.cms.interfaces.CriminalInterface;
import com.security.cms.models.Crime;
import com.security.cms.models.Criminal;
import com.security.cms.repos.CrimeRepository;
import com.security.cms.repos.CriminalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class CriminalService implements CriminalInterface {

    private final CriminalRepository criminalRepository;
    private final CrimeRepository crimeRepository;

    public CriminalService(CriminalRepository criminalRepository,CrimeRepository crimeRepository) {
        this.criminalRepository = criminalRepository;
        this.crimeRepository=crimeRepository;
    }

    @Override
    public List<Criminal> criminals() {
        try{
            return criminalRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> criminalById(Long id) {
             Optional<Criminal> criminal=criminalRepository.findById(id);
             if(criminal.isPresent()){
                 return ResponseEntity.ok(criminal);
             }
             return ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity<String> addCriminal(Criminal criminal){
        try {
           Optional<Criminal> existedCriminal= criminalRepository.getCriminalByName(criminal.getName());
           if(existedCriminal.isPresent()){
               throw new  ResourceNotFoundException(String.format("Criminal already exists with the name %s",criminal.getName()));
           }
           System.out.println(criminal);
           if(criminal.getCrimes()!=null){
               for(Crime crime : criminal.getCrimes()){
                   crime.setCriminal(criminal);
               }
           }
           criminalRepository.save(criminal);
           return ResponseEntity.created(URI.create("/criminals")).body("Criminal record created successfully.");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateCriminal(Criminal criminal, Long id) {
        Optional<Criminal> existedCriminal=criminalRepository.findById(id);
        if(existedCriminal.isPresent()){
            if(criminal.getName()!=null){
                existedCriminal.get().setName(criminal.getName());
            }

            if(criminal.getGender()!=null){
                existedCriminal.get().setGender(criminal.getGender());
            }
            if(criminal.getAddress()!=null){
                existedCriminal.get().setAddress(criminal.getAddress());
            }
            criminalRepository.save(existedCriminal.get());
            return ResponseEntity.ok("Criminal record updated successfully");
        }
        return ResponseEntity.badRequest().body("Criminal record not found");
    }


    @Override
    public ResponseEntity<String> deleteCriminal(Long criminalId) {
        Optional<Criminal> criminal=criminalRepository.findById(criminalId);
        if(criminal.isPresent()){
            criminalRepository.deleteById(criminalId);
            return ResponseEntity.noContent().build();
        }
        throw new ResourceNotFoundException("Criminal record does not found");
    }
}
