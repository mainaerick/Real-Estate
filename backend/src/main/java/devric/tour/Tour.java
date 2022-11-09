package devric.tour;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import devric.appuser.AppUser;
import devric.property.Property;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String tour_type;
    private String tour_date;

    @JsonIgnore
    @ManyToOne
    private Property property;
    @JsonIgnore
    @ManyToOne
    private AppUser appUser;
    public Tour(){

    }

    public Tour(Long id, String tour_type, String tour_date, Property property, AppUser appUser) {
        this.id = id;
        this.tour_type = tour_type;
        this.tour_date = tour_date;
        this.property = property;
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTour_type() {
        return tour_type;
    }

    public void setTour_type(String tour_type) {
        this.tour_type = tour_type;
    }

    public String getTour_date() {
        return tour_date;
    }

    public void setTour_date(String tour_date) {
        this.tour_date = tour_date;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
