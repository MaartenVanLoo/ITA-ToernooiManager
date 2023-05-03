package be.ita.toernooimanager.service;

import be.ita.toernooimanager.model.InfoEntity;
import be.ita.toernooimanager.repositories.InfoRepository;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class InfoService {
    private final InfoRepository infoRepository;

    public List<InfoEntity> findAll(){
        return infoRepository.findAll();
    }
}
