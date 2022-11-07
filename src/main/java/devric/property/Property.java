package devric.property;

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
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String property_name;
    private String property_description;
    private Long property_price;
    private String property_location;
    private int bedroom;
    private int bathroom;

    private String features;
    private String property_type;
    private String status;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;

    public Property() {

    }

    public Property(Long id, String property_name, String property_description,
                    Long property_price, String property_location,
                    int bedroom, int bathroom, String features,
                    String property_type, String status, AppUser user) {
        this.id = id;
        this.property_name = property_name;
        this.property_description = property_description;
        this.property_price = property_price;
        this.property_location = property_location;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.features = features;
        this.property_type = property_type;
        this.status = status;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProperty_name() {
        return property_name;
    }

    public void setProperty_name(String property_name) {
        this.property_name = property_name;
    }

    public String getProperty_description() {
        return property_description;
    }

    public void setProperty_description(String property_description) {
        this.property_description = property_description;
    }

    public Long getProperty_price() {
        return property_price;
    }

    public void setProperty_price(Long property_price) {
        this.property_price = property_price;
    }

    public String getProperty_location() {
        return property_location;
    }

    public void setProperty_location(String property_location) {
        this.property_location = property_location;
    }

    public int getBedroom() {
        return bedroom;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public int getBathroom() {
        return bathroom;
    }

    public void setBathroom(int bathroom) {
        this.bathroom = bathroom;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
