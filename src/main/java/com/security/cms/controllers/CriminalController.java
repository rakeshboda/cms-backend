package com.security.cms.controllers;

import com.security.cms.models.Criminal;
import com.security.cms.service.CriminalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/criminals")
@CrossOrigin(origins = "http://localhost:3000")
public class CriminalController {

    private final CriminalService criminalService;

    public CriminalController(CriminalService criminalService) {
        this.criminalService = criminalService;
    }

    @GetMapping()
    public List<Criminal> criminals(){
        return criminalService.criminals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> criminalById(@PathVariable Long id){
        return criminalService.criminalById(id);
    }

    @PostMapping("/")
    public ResponseEntity<String> addCriminal( @RequestBody @Valid Criminal criminal){
        return criminalService.addCriminal(criminal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCriminal(@RequestBody Criminal criminal, @PathVariable Long id){
        return criminalService.updateCriminal(criminal,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCriminal(@PathVariable Long id){
        return criminalService.deleteCriminal(id);
    }
}
