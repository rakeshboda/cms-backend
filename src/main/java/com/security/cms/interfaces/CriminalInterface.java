package com.security.cms.interfaces;

import com.security.cms.models.Criminal;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CriminalInterface {
    List<Criminal> criminals();
    ResponseEntity<?> criminalById(Long id);
    ResponseEntity<String> addCriminal(Criminal criminal);
    ResponseEntity<?> updateCriminal(Criminal criminal, Long id);
    ResponseEntity<String> deleteCriminal(Long criminalId);
}
