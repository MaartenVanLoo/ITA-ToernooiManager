package be.ita.toernooimanager.service;


import be.ita.toernooimanager.model.CatdefEntity;
import be.ita.toernooimanager.repositories.CatdefRepository;
import be.ita.toernooimanager.service.Mapper.CatdefMapper;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CatdefService{
    CatdefRepository catdefRepository;
    CatdefMapper catdefMapper;
    public List<CatdefEntity> findAll(){
        return catdefRepository.findAll().stream().map(catdefMapper::mapObject).toList();
    }
}
