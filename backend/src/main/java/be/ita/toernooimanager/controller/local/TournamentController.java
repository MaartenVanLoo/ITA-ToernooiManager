package be.ita.toernooimanager.controller.local;

import be.ita.toernooimanager.model.local.Tournament;
import be.ita.toernooimanager.service.local.TournamentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/tournament")
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;

    @GetMapping
    //@PreAuthorize("hasAuthority('admin_read')")
    public ResponseEntity<Page<Tournament>> getAllTournaments(@PageableDefault(value = 30) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("[GET] Tournaments");
        Page<Tournament> tournaments = tournamentService.getAllTournaments(pageable);

        return ResponseEntity.ok().body(tournaments);
        //return ResponseEntity.ok().body(tournaments.map(u -> modelMapper.map(u, TournamentDto.class)));
    }
}