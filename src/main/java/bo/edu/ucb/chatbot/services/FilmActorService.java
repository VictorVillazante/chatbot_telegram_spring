package bo.edu.ucb.chatbot.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import bo.edu.ucb.chatbot.models.dto.Film;
import bo.edu.ucb.chatbot.models.entities.FilmActorEntity;
import bo.edu.ucb.chatbot.repositories.FilmActorRepository;
import bo.edu.ucb.chatbot.util.converters.FilmConverter;

@Service
public class FilmActorService {
    @Autowired
    FilmActorRepository filmActorRepository;

    @Autowired
    private FilmConverter filmConverter;


    @Transactional(readOnly = true)
    public List<Film> findByFullNameActor(String actor) {
        List<FilmActorEntity>filmActorList=filmActorRepository.findFilmActorListByFullnameActor(actor);
        return filmActorList.stream().map((FilmActorEntity filmActorEntity)->filmConverter.entityToDto(filmActorEntity.getFilm())).collect(Collectors.toList());
    }


}
