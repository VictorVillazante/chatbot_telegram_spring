package bo.edu.ucb.chatbot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bo.edu.ucb.chatbot.models.entities.FilmActorEntity;
import bo.edu.ucb.chatbot.models.entities.embedded_ids.FilmActorId;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActorEntity, FilmActorId> {

    @Query(value="SELECT DISTINCT fa FROM FilmActorEntity fa "+
    "JOIN fa.actor a "+
    "JOIN fa.film f "+
    "WHERE CONCAT(a.firstName, ' ', a.lastName) LIKE %?1%")
    List<FilmActorEntity> findFilmActorListByFullnameActor(String nombreActor);
}