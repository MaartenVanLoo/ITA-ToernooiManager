package be.ita.toernooimanager.controller.shiai;

import be.ita.toernooimanager.model.shiai.CompetitorsEntity;
import be.ita.toernooimanager.service.shiai.CompetitorsService;
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
@RequestMapping("/competitors")
@RequiredArgsConstructor
public class CompetitorsController {
    private final CompetitorsService competitorsService;

    @GetMapping
    @Transactional
    @PreAuthorize("hasAuthority('logon')")
    public ResponseEntity<?> getCompetitors(){
        log.info("GET: /competitors");
        List<CompetitorsEntity> entity= competitorsService.findAll();
        return ResponseEntity.ok().body(entity);
    }
}
