package be.ita.toernooimanager.controller;

import be.ita.toernooimanager.model.MatchesEntity;
import be.ita.toernooimanager.service.MatchesService;
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
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchesController {
    private final MatchesService matchesService;

    @GetMapping
    @Transactional
    public ResponseEntity<?> getMatches(){
        log.info("GET: /matches");
        List<MatchesEntity> entity= matchesService.findAll();
        return ResponseEntity.ok().body(entity);
    }
}
