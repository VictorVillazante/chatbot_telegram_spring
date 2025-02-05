package bo.edu.ucb.chatbot.bl;

import bo.edu.ucb.chatbot.dao.FilmDao;
import bo.edu.ucb.chatbot.exception.SakilaException;
import bo.edu.ucb.chatbot.models.dto.Film;
import bo.edu.ucb.chatbot.repositories.FilmRepository;
import bo.edu.ucb.chatbot.util.converters.FilmConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmSearchBl {

    private final FilmDao filmDao;

    @Autowired
    public FilmSearchBl(FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmConverter filmConverter;

    public List<Film> findByTitle(String title) {
        if (title == null || title.trim().equals("")) {
            throw new SakilaException(403, "Bad request: The title parameter is mandatory and can't be null or empty");
        }
        System.out.println("--------------------------------------------------------");
        System.out.println(title);
        String[] p=title.split("\\|");
        System.out.println(Arrays.asList(p));
        if(p.length>1){
            String[] o1=p[0].split("\\=");
            System.out.println(Arrays.asList(o1));
            String[] o2=p[1].split("\\=");
            System.out.println(Arrays.asList(o2));
            String titulo=(o1[0].trim().toLowerCase().equals("titulo"))?o1[1].trim().toLowerCase():(o2[0].trim().toLowerCase().equals("titulo"))?o2[1].trim().toLowerCase():"";
            String actor=(o1[0].trim().toLowerCase().equals("actor"))?o1[1].trim().toLowerCase():(o2[0].trim().toLowerCase().equals("actor"))?o2[1].trim().toLowerCase():"";
            if(!titulo.trim().equals("") && !actor.trim().equals("")){
                return busquedaPorTituloActor(titulo,actor);
            }

        }else{
            String[] b=title.split("\\=");
            if(b.length>1){
                switch(b[0].trim().toLowerCase()){
                    case "titulo":
                        return busquedaPorTitulo(b[1].trim().toLowerCase());
                    case "actor":
                        return busquedaPorActor(b[1].trim().toLowerCase());
                }

            }
           
        }
        
        /*if(o1.length>2 && o2.length>2){
            System.out.println(o1[0]);
            System.out.println(o1[1]);
            System.out.println(o2[0]);
            System.out.println(o2[1]);
        }*/
        System.out.println("--------------------------------------------------------");
        return null;
    }

    private List<Film> busquedaPorActor(String actor) {
        System.out.println("busqueda por actor");
        return filmDao.findByActor(actor);
    }

    private List<Film> busquedaPorTitulo(String titulo) {
        System.out.println("busqueda por titulo");
        return filmRepository.findByTitle(titulo).stream().map(filmConverter::entityToDto).collect(Collectors.toList());
    }

    private List<Film> busquedaPorTituloActor(String titulo, String actor) {
        System.out.println("busqueda por titulo y actor");
        return filmDao.findByTitleActor(titulo,actor);
    }
   
    
}
