package bo.edu.ucb.chatbot.util.converters;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bo.edu.ucb.chatbot.models.dto.Film;

@Component
public class FilmConverter {

    @Autowired
    private EntityManager entityManager;

    public Film entityToDto(Film entity) {
        if (entity == null) {
            return null;
        }

        Film dto = new Film();

        dto.setFilmId(entity.getFilmId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setReleaseYear((short) (entity.getReleaseYear() != null ? entity.getReleaseYear() : 0));
        // dto.setLanguage(entity.getLanguageId() != null ? entity.getLanguageId().toString() : "");  // Esto puede requerir lógica adicional si `language` es una entidad
        // dto.setOriginalLanguage(entity.getOriginalLanguageId() != null ? entity.getOriginalLanguageId().toString() : "");
        dto.setLength(entity.getLength());
        dto.setRating(entity.getRating());
        dto.setSpecialFeatures(entity.getSpecialFeatures());
        
        // dto.setLastUpdate(entity.getLastUpdate() != null ? new Date(entity.getLastUpdate().getTime()) : null);


        return dto;
    }

    // Método para convertir un DTO a una entidad Film
    public Film dtoToEntity(Film dto) {
        if (dto == null) {
            return null;
        }

        Film entity = new Film();

        entity.setFilmId(dto.getFilmId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setReleaseYear((dto.getReleaseYear() != null ? dto.getReleaseYear() : 0));
        // entity.setLanguageId(Integer.parseInt(dto.getLanguage())); // Si Language es un ID
        // entity.setOriginalLanguageId(Integer.parseInt(dto.getOriginalLanguage())); // Si OriginalLanguage es un ID
        entity.setLength(dto.getLength());
        entity.setRating(dto.getRating());
        entity.setSpecialFeatures(dto.getSpecialFeatures());
        
        // entity.setLastUpdate(dto.getLastUpdate() != null ? new java.sql.Timestamp(dto.getLastUpdate().getTime()) : null);

        return entity;
    }
}