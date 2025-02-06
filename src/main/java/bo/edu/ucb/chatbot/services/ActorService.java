package bo.edu.ucb.chatbot.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.edu.ucb.chatbot.models.dto.ActorDto;
import bo.edu.ucb.chatbot.repositories.ActorRepository;
import bo.edu.ucb.chatbot.util.converters.ActorConverter;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorConverter actorConverter;

    public List<ActorDto> findAllActorsFromIdFilm(Integer idFilm){
        return actorRepository.findAllActorsFromIdFilm(idFilm).stream().map(actorConverter::entityToDto).collect(Collectors.toList());
    }
}
