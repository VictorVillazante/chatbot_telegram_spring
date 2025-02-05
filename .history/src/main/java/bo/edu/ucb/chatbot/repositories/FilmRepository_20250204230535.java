package bo.edu.ucb.chatbot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bo.edu.ucb.chatbot.models.entities.FilmEntity;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {

    List<FilmEntity> findByTitle(String title);

}