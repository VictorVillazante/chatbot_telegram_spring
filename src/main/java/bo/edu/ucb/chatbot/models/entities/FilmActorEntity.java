package bo.edu.ucb.chatbot.models.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import bo.edu.ucb.chatbot.models.entities.embedded_ids.FilmActorId;
import lombok.Data;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "film_actor")
public class FilmActorEntity {

    @EmbeddedId
    private FilmActorId filmActorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("actorId")
    @JoinColumn(name = "actor_id")
    private ActorEntity actor;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    private FilmEntity film;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    private Date lastUpdate;
}

