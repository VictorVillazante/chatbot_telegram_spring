package bo.edu.ucb.chatbot.util.converters;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bo.edu.ucb.chatbot.models.dto.Film;
import bo.edu.ucb.chatbot.models.entities.FilmEntity;

@Component
public class FilmConverter {

    @Autowired
    private EntityManager entityManager;

    public Film entityToDto(FilmEntity entity) {
        if (entity == null) {
            return null;
        }

        Film dto = new Film();

        dto.setFilmId(entity.getFilmId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setReleaseYear((short) (entity.getReleaseYear() != null ? entity.getReleaseYear() : 0));
        dto.setLength(entity.getLength());
        dto.setRating(entity.getRating());
        dto.setSpecialFeatures(entity.getSpecialFeatures());
        


        return dto;
    }
    public Film dtoToEntity(Film dto) {
        if (dto == null) {
            return null;
        }

        Film entity = new Film();

        entity.setFilmId(dto.getFilmId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setReleaseYear((dto.getReleaseYear() != null ? dto.getReleaseYear() : 0));
        entity.setLength(dto.getLength());
        entity.setRating(dto.getRating());
        entity.setSpecialFeatures(dto.getSpecialFeatures());
        return entity;
    }
}