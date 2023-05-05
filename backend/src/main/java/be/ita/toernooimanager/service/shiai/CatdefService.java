package be.ita.toernooimanager.service.shiai;


import be.ita.toernooimanager.model.shiai.CatdefEntity;
import be.ita.toernooimanager.repositories.shiai.CatdefRepository;
import be.ita.toernooimanager.service.shiai.mapper.CatdefMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CatdefService{
    CatdefRepository catdefRepository;
    CatdefMapper catdefMapper;
    public List<CatdefEntity> findAll(){
        return catdefRepository.findAll().stream().map(catdefMapper::mapObject).toList();
    }
}
