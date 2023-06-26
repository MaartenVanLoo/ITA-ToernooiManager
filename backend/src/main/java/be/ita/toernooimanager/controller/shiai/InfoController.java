package be.ita.toernooimanager.controller.shiai;

import be.ita.toernooimanager.model.shiai.InfoEntity;
import be.ita.toernooimanager.service.shiai.InfoService;
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
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {
    private final InfoService infoService;

    @GetMapping
    @Transactional
    @PreAuthorize("hasAuthority('logon')")
    public ResponseEntity<?> getInfo(){
        log.info("GET: /info");
        List<InfoEntity> entity= infoService.findAll();
        return ResponseEntity.ok().body(entity);
    }
}
