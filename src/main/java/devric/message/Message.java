package devric.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import devric.appuser.AppUser;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String message;
    @JsonIgnore
    @ManyToOne
    private AppUser appUser;

}
