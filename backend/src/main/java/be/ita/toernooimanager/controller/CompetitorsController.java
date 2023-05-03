package be.ita.toernooimanager.controller;

import be.ita.toernooimanager.model.CompetitorsEntity;
import be.ita.toernooimanager.service.CompetitorsService;
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
@RequestMapping("/competitors")
@RequiredArgsConstructor
public class CompetitorsController {
    private final CompetitorsService competitorsService;

    @GetMapping
    @Transactional
    public ResponseEntity<?> getCompetitors(){
        log.info("GET: /competitors");
        List<CompetitorsEntity> entity= competitorsService.findAll();
        return ResponseEntity.ok().body(entity);
    }
}
