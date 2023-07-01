package be.ita.toernooimanager.service.local;

import be.ita.toernooimanager.repositories.local.PoolRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@AllArgsConstructor
@Validated
public class PoolService {
    PoolRepository poolRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void removeAll(){
        log.info("Delete all pool's");
        poolRepository.deleteAll();
    }
}
