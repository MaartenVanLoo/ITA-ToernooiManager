package be.ita.toernooimanager.controller.shiai;

import be.ita.toernooimanager.model.shiai.MatchesEntity;
import be.ita.toernooimanager.service.shiai.MatchesService;
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
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchesController {
    private final MatchesService matchesService;

    @GetMapping
    @Transactional
    @PreAuthorize("hasAuthority('logon')")
    public ResponseEntity<?> getMatches(){
        log.info("GET: /matches");
        List<MatchesEntity> entity= matchesService.findAll();
        return ResponseEntity.ok().body(entity);
    }
}
