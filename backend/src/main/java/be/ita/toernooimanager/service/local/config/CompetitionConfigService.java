package be.ita.toernooimanager.service.local.config;

import be.ita.toernooimanager.model.local.config.CompetitionConfig;
import be.ita.toernooimanager.model.local.config.PouleSettings;
import be.ita.toernooimanager.repositories.local.config.CompetitionConfigRepository;
import be.ita.toernooimanager.repositories.local.config.PouleSettingsRepository;
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Getter
@Setter
@Slf4j
@AllArgsConstructor
public class CompetitionConfigService {
    private final CompetitionConfigRepository competitionConfigRepository;

    private final ExclusionStrategy exclusionStrategy;
    //load from file
    public void load(String filename) {
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(filename));
            List<CompetitionConfig> settings =  new ArrayList<>(Arrays.asList(gson.fromJson(reader, CompetitionConfig[].class)));

            if (settings.isEmpty()) return;

            //clear current settings
            this.clear();

            //fill with loaded settings
            competitionConfigRepository.saveAll(settings);
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
            gson.toJson(competitionConfigRepository.findAll(),writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    private void clear(){
        log.info("Clear competition config settings");
        competitionConfigRepository.deleteAll();
    }
}
