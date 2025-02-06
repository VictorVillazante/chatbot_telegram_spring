package bo.edu.ucb.chatbot.models.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorDto {
    private Integer actorId;
    private String firstName;
    private String lastName;
    private Date lastUpdate;
}
