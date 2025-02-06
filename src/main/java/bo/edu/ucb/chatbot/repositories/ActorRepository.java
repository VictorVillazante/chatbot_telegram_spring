package bo.edu.ucb.chatbot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bo.edu.ucb.chatbot.models.entities.ActorEntity;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Integer> {


    @Query(value="SELECT DISTINCT a from FilmActorEntity fa "+
    "JOIN fa.actor a "+
    "JOIN fa.film f "+
    "WHERE f.filmId=?1")
    List<ActorEntity> findAllActorsFromIdFilm(Integer idFilm);

}