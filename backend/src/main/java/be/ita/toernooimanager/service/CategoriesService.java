package be.ita.toernooimanager.service;

import be.ita.toernooimanager.model.CategoriesEntity;
import be.ita.toernooimanager.repositories.CategoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriesService{
    private final CategoriesRepository categoriesRepository;
    public List<CategoriesEntity> findAll(){
        return categoriesRepository.findAll();
    }
}
