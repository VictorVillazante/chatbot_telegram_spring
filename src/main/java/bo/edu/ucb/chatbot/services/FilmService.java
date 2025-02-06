package bo.edu.ucb.chatbot.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import bo.edu.ucb.chatbot.models.dto.Film;
import bo.edu.ucb.chatbot.repositories.FilmRepository;
import bo.edu.ucb.chatbot.util.converters.FilmConverter;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmConverter filmConverter;

    public List<Film> findByTitle(String title) {
        return filmRepository.findByTitleContaining(title).stream().map(filmConverter::entityToDto).collect(Collectors.toList());
    }
}
