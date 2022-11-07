package devric.review;


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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private String title;
    private int rating;
    private String review;
    @JsonIgnore
    @ManyToOne
    private Property property;
    @JsonIgnore
    @ManyToOne
    private AppUser appUser;

    public Review() {
    }

    public Review(Long id, String title, int rating, String review, Property property, AppUser appUser) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.review = review;
        this.property = property;
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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
