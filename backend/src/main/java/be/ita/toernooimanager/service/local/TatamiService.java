package be.ita.toernooimanager.service.local;

import be.ita.toernooimanager.model.local.Tatami;
import be.ita.toernooimanager.repositories.local.TatamiRepository;
import be.ita.toernooimanager.service.Exceptions.AlreadyExistsException;
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
public class TatamiService {
    TatamiRepository tatamiRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Tatami createTatami(Integer number) throws AlreadyExistsException {
        if (tatamiRepository.findByNumber(number).isPresent()){
            throw new AlreadyExistsException("Tatami " + number +" already exists.");
        }
        Tatami tatami = new Tatami(number);
        tatami = tatamiRepository.save(tatami);
        return tatami;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void removeAll(){
        log.info("Delete all tatamis");
        tatamiRepository.deleteAll();
    }
}
