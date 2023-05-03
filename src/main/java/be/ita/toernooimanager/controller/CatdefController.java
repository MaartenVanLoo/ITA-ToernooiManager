package be.ita.toernooimanager.controller;

import be.ita.toernooimanager.model.CatdefEntity;
import be.ita.toernooimanager.service.CatdefService;
import be.ita.toernooimanager.service.Mapper.CatdefMapper;
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
@RequestMapping("/catdef")
@RequiredArgsConstructor
public class CatdefController {
    private final CatdefService catdefService;
    private final CatdefMapper catdefMapper;
    @GetMapping
    @Transactional
    public ResponseEntity<?> getCatdef(){
        log.info("GET: /catdef");
        List<CatdefEntity> entity= catdefService.findAll();
        return ResponseEntity.ok().body(entity.stream().map(catdefMapper::mapEntity2GetDto).toList());
    }
}
