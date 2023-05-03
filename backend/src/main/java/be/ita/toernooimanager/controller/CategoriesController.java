package be.ita.toernooimanager.controller;

import be.ita.toernooimanager.model.CategoriesEntity;
import be.ita.toernooimanager.repositories.CategoriesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getCategories(){
        log.info("GET: /categories");
        List<CategoriesEntity> entity= categoriesRepository.findAll();
        return ResponseEntity.ok().body(entity);
    }
}
