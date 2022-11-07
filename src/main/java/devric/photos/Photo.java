package devric.photos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import devric.property.Property;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private String path;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Property property;
    public Photo() {

    }
    public Photo(Long id, String path, Property property) {
        this.id = id;
        this.path = path;
        this.property = property;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
