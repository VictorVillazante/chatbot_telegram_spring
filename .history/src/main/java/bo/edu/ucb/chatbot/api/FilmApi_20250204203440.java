package bo.edu.ucb.chatbot.api;

import bo.edu.ucb.chatbot.bl.FilmSearchBl;
import bo.edu.ucb.chatbot.config.BotConfig;
import bo.edu.ucb.chatbot.dto.Film;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class FilmApi {

    FilmSearchBl filmSearchBl;

    private static final Logger LOGGER = LoggerFactory.getLogger(BotConfig.class);

    @Autowired
    public FilmApi(FilmSearchBl filmSearchBl) {
        this.filmSearchBl = filmSearchBl;
    }

    @GetMapping(value = "/film/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Film>> findBytTitle(@PathVariable String title) {
        LOGGER.info("Invocando al metodo GET findBytTitle!!!!!!!!!!!");
        return new ResponseEntity<>(filmSearchBl.findByTitle(title),HttpStatus.OK);
    }
}
