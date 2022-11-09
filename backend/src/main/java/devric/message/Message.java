package devric.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import devric.property.Property;
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
    @ManyToOne
    private Property property;

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
