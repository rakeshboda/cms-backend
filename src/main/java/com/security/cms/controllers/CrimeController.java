package com.security.cms.controllers;

import com.security.cms.models.Crime;
import com.security.cms.repos.CrimeRepository;
import com.security.cms.service.CrimieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crimes")
@CrossOrigin(origins = "http://localhost:3000")
public class CrimeController {

    private final CrimieService crimieService;
    private final CrimeRepository crimeRepository;

    public CrimeController(CrimieService crimieService, CrimeRepository crimeRepository) {
        this.crimieService = crimieService;
        this.crimeRepository = crimeRepository;
    }

    @GetMapping
    public List<Crime> getAllCrimes(){
        return crimeRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<Crime> getCrimesByCriminalId(@PathVariable Long id){
        return crimieService.ciminalCrimes(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addCrime(@RequestBody Crime crime,@PathVariable Long id){
        return crimieService.addCrime(crime,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCrime(@PathVariable Long id){
        return crimieService.deleteCrime(id);
    }

}
