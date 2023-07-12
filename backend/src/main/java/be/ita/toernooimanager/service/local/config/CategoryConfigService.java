package be.ita.toernooimanager.service.local.config;

import be.ita.toernooimanager.model.local.config.CategoryConfig;
import be.ita.toernooimanager.repositories.local.config.CategoryConfigRepository;
import be.ita.toernooimanager.service.Exceptions.ResourceNotFoundException;
import be.ita.toernooimanager.utils.LocalDateTimeTypeAdapter;
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryConfigService {
    private final CategoryConfigRepository categoryConfigRepository;

    private final ExclusionStrategy exclusionStrategy;
    //load from file
    public void load(String filename) {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                    .create();

            JsonReader reader = new JsonReader(new FileReader(filename));
            List<CategoryConfig> settings =  new ArrayList<>(Arrays.asList(gson.fromJson(reader, CategoryConfig[].class)));

            if (settings.isEmpty()) return;

            //clear current settings
            this.clear();

            //fill with loaded settings
            categoryConfigRepository.saveAll(settings);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    public void save(String filename){
        try{
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .setExclusionStrategies(exclusionStrategy)
                    .create();

            Writer writer = new FileWriter(filename,false);
            gson.toJson(categoryConfigRepository.findAll(),writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    private void clear(){
        log.info("Clear competition config settings");
        categoryConfigRepository.deleteAll();
    }

    //Getters & setters
    public CategoryConfig getCategoryConfig(String config){
        CategoryConfig configuration = categoryConfigRepository.findByCategory(config).orElseThrow(()->new ResourceNotFoundException(config));
        return configuration;
    }
}
