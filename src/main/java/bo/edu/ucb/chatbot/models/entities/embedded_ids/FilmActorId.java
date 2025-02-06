package bo.edu.ucb.chatbot.models.entities.embedded_ids;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class FilmActorId implements java.io.Serializable {
    private Integer actorId;
    private Integer filmId;
}

