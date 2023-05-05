package be.ita.toernooimanager.service.shiai.mapper;

import be.ita.toernooimanager.controller.DTO.GetCatdefDto;
import be.ita.toernooimanager.model.shiai.CatdefEntity;
import be.ita.toernooimanager.model.shiai.CatdefObject;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CatdefMapper {
    private final ModelMapper modelMapper;

    public CatdefEntity mapObject(CatdefObject obj){
        return obj.getId();
    }
    public GetCatdefDto mapEntity2GetDto(CatdefEntity entity){
        return modelMapper.map(entity, GetCatdefDto.class);
    }
}
