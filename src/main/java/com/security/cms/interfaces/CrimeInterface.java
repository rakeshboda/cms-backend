package com.security.cms.interfaces;

import com.security.cms.models.Crime;
import org.springframework.http.ResponseEntity;

import java.util.*;

public interface CrimeInterface {
    List<Crime>  ciminalCrimes(Long id);
    ResponseEntity<?> addCrime(Crime crime,Long id);
    ResponseEntity<?> deleteCrime(Long id);
}
