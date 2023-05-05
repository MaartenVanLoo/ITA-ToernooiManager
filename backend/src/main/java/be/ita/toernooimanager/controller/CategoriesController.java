package be.ita.toernooimanager.controller;

import be.ita.toernooimanager.model.shiai.CategoriesEntity;
import be.ita.toernooimanager.repositories.shiai.CategoriesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesRepository categoriesRepository;

    @GetMapping
    @Transactional
    @PreAuthorize("hasAuthority('logon')")
    public ResponseEntity<?> getCategories(){
        log.info("GET: /categories");
        List<CategoriesEntity> entity= categoriesRepository.findAll();
        return ResponseEntity.ok().body(entity);
    }
}
