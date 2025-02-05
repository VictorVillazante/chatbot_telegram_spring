package bo.edu.ucb.chatbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bo.edu.ucb.chatbot.models.entities.FilmEntity;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {
}