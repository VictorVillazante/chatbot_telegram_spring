package bo.edu.ucb.chatbot.models.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "language")
public class LanguageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "language_id")
    private Integer languageId;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    private Date lastUpdate;
}