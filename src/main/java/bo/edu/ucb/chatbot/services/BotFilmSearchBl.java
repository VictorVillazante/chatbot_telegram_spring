package bo.edu.ucb.chatbot.services;

import org.springframework.beans.factory.annotation.Autowired;

import bo.edu.ucb.chatbot.models.dto.ActorDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class BotFilmSearchBl {


    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmActorService filmActorService;

    @Autowired
    private ActorService actorService;

    Map<String,String>chats=new HashMap<>();

    public List<String> processMessage(String chatId,String message) {
        List<String> result = new ArrayList<>();
        if(chats.get(chatId)!=null){
            String responseKey=getKeyResponse(chats.get(chatId),message);
            List<String> respuestas=sendElectionResponses(responseKey,message);
            result.addAll(respuestas);
            chats.replace(chatId,responseKey);
            return result;
        }
        result.add(mainResponses.get("welcomeMessage"));
        chats.put(chatId,"welcomeMessage");
        return result;
    }
    private String getKeyResponse(String keyActualMain, String message) {
        String futureMainKey=keyActualMain;
        if(keyActualMain.equals("findMovie") ){
            futureMainKey=findMovie.get(message);
        }
        if(keyActualMain.equals("welcomeMessage") ){
            futureMainKey=welcomeMessage.get(message);
        }
        if(keyActualMain.equals("findMovieByTitle") ){
            futureMainKey=(!message.equals("exit"))?"responseFindMovieByTitle":"findMovie";
        }
        if(keyActualMain.equals("findMovieByActor") ){
            futureMainKey=(!message.equals("exit"))?"responseFindMovieByActor":"findMovie";
        }
        if(keyActualMain.equals("responseFindMovieByTitle") ){
            futureMainKey=(!message.equals("exit"))?"responseFindMovieByTitle":"findMovie";
        }
        if(keyActualMain.equals("responseFindMovieByActor") ){
            futureMainKey=(!message.equals("exit"))?"responseFindMovieByActor":"findMovie";
        }
        if(keyActualMain.equals("getAttentionSchedule") ){
            futureMainKey=(!message.equals("exit"))?"getAttentionSchedule":"welcomeMessage";
        }
        return futureMainKey;
    }
    final Map<String, String> mainResponses = Map.ofEntries(
        Map.entry("findMovie", "Puede buscar la pelicula por actor o titulo\n1.Actor\n2.Titulo"),
        Map.entry("getAttentionSchedule", "Horarios de atencion Lun-Vie 08:00-19:00"),
        Map.entry("findMovieByTitle", "Ingrese titulo de pelicula"),
        Map.entry("findMovieByActor", "Ingrese nombre de actor"),
        Map.entry("welcomeMessage", "BIENVENIDO A TIENDA DE VIDEOS UWU \n"+
        "Seleccione la opcion que necesites\n"+
        "1.Buscar pelicula\n"+
        "2.Horarios de atencion\n")
    );
    final Map<String, String> findMovie = Map.ofEntries(
        Map.entry("1", "findMovieByActor"),
        Map.entry("2", "findMovieByTitle"),
        Map.entry("exit", "welcomeMessage")
    );

    final Map<String, String> welcomeMessage = Map.ofEntries(
        Map.entry("1", "findMovie"),
        Map.entry("2", "getAttentionSchedule"),
        Map.entry("exit", "welcomeMessage")
    );
    private List<String> sendElectionResponses(String responseKey,String userMessage) {
        List<String>respuestas=new ArrayList<>();
        if(responseKey.equals("responseFindMovieByTitle") ){
            List<String>listaPeliculas=filmService.findByTitle(userMessage).stream().map(film -> {
                List<String>filmActors=actorService.findAllActorsFromIdFilm(film.getFilmId()).stream().map((ActorDto actorDto)->actorDto.toString()).collect(Collectors.toList());
                return film.toString()+"\nActores:"+String.join("\n", filmActors);
            }).collect(Collectors.toList());
            return listaPeliculas;
        }
        if(responseKey.equals("responseFindMovieByActor") ){
            List<String>listaPeliculas=filmActorService.findByFullNameActor(userMessage).stream().map(film->{
                List<String>filmActors=actorService.findAllActorsFromIdFilm(film.getFilmId()).stream().map((ActorDto actorDto)->actorDto.toString()).collect(Collectors.toList());
                return film.toString()+"\nActores:"+String.join("\n", filmActors);
            }).collect(Collectors.toList());
            return listaPeliculas;
        }
        respuestas.add(mainResponses.get(responseKey));
        return respuestas;
    }
}

