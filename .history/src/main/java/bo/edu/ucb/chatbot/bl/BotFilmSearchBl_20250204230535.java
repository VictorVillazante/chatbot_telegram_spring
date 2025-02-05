package bo.edu.ucb.chatbot.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bo.edu.ucb.chatbot.models.dto.Film;

import java.util.ArrayList;
import java.util.List;

/**
 * Procesar la lógica de negocio de las conversaciones del bo.
 * Recibe los mensajes enviados por el usuario como String.
 * Y restorna una List<String> como respuesta a estos mensajes
 * Con el proposito de hacer High Cohesion esta clase no debería utilizar ningun API de Telegram
 */

@Component
public class BotFilmSearchBl {

    private FilmSearchBl filmSearchBl;

    @Autowired
    public BotFilmSearchBl(FilmSearchBl filmSearchBl) {
        this.filmSearchBl = filmSearchBl;
    }

    public List<String> processMessage(String message) {
        List<String> result = new ArrayList<>();

        List<Film> filmList =  filmSearchBl.findByTitle(message);

        if (!filmList.isEmpty()) {
            result.add("Encontré las siguientes películas:");
            for (Film film : filmList) {
                result.add(film.getTitle());
            }
        } else {
            result.add("No encontré ninguna película para: " + message);
        }

        return result;
    }
}
