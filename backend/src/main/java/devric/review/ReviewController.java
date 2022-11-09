package devric.review;

import devric.exception_handler.PropertyNotFound;
import devric.property.Property;
import devric.property.PropertyRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    PropertyRepository propertyRepository;
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ReviewController.all}", response = Review.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<Review> getAll(){
        return reviewRepository.findAll();
    }

    @GetMapping("/{reviewId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ReviewController.Review}", response = Review.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "Review doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public EntityModel<Review> getReviewById(@PathVariable long reviewId) {
        Review review= reviewRepository.findById(reviewId).get();
        if (null == review)
            throw new PropertyNotFound("Review not found");
        EntityModel model = EntityModel.of(review);
        Link link = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll()).withRel("all-Review");
        model.add(link);
        return model;
    }

    @PostMapping("/add/{propertyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ReviewController.addReview}", response = Review.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "Property doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<Object> saveReview(@PathVariable Long propertyId, @Valid @RequestBody Review review) {
        Property property = propertyRepository.findById(propertyId).get();
        if (null == property) {
            throw new PropertyNotFound("User Not Found");
        }
        review.setProperty(property);
        reviewRepository.save(review);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{departmentId}")
                .buildAndExpand(property.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping("/delete/{reviewId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ReviewController.delete}", response = Review.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "Review doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public void deleteReview(@PathVariable Long reviewId) {
        Review review = reviewRepository.findById(reviewId).get();
        if (null == review)
            throw new PropertyNotFound("Review not found");
        reviewRepository.delete(review);
    }
}
