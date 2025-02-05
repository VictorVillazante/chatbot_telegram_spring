package bo.edu.ucb.chatbot.models.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Film {
    private Integer filmId;
    private String title;
    private String description;
    private Short releaseYear;
    private String language;
    private String originalLanguage;
    private Integer length;
    private String lengthLabel;
    private String rating;
    private String specialFeatures;
    private List<String> actors;
    private String category;
    private Date lastUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return filmId.equals(film.filmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId);
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", lengthLabel='" + lengthLabel + '\'' +
                '}';
    }

   

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        if (length != null) {
            int hours = length / 60;
            int minutes = length % 60;
            if (hours > 0) {
                lengthLabel = hours + "h " + minutes + "m";
            } else {
                lengthLabel = minutes + "m";
            }

        }
        this.length = length;
    }  
}
