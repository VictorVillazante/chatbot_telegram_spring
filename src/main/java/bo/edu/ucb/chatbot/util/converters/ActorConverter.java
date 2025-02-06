package bo.edu.ucb.chatbot.util.converters;

import org.springframework.stereotype.Component;

import bo.edu.ucb.chatbot.models.dto.ActorDto;
import bo.edu.ucb.chatbot.models.entities.ActorEntity;

@Component
public class ActorConverter {

    public ActorDto entityToDto(ActorEntity entity) {
        if (entity == null) {
            return null;
        }
        ActorDto dto = new ActorDto();
        dto.setActorId(entity.getActorId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setLastUpdate(entity.getLastUpdate());
        return dto;
    }

    public ActorEntity dtoToEntity(ActorDto dto) {
        if (dto == null) {
            return null;
        }
        ActorEntity entity = new ActorEntity();
        entity.setActorId(dto.getActorId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setLastUpdate(dto.getLastUpdate());
        return entity;
    }
}
