package be.ita.toernooimanager.service.shiai;

import be.ita.toernooimanager.model.shiai.InfoEntity;
import be.ita.toernooimanager.repositories.shiai.InfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InfoService {
    private final InfoRepository infoRepository;

    public List<InfoEntity> findAll(){
        return infoRepository.findAll();
    }
}
