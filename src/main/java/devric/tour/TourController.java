package devric.tour;

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
@RequestMapping("/api/tour")
public class TourController {
    @Autowired
    TourRepository tourRepository;
    @Autowired
    PropertyRepository propertyRepository;
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${TourController.all}", response = Tour.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<Tour> getAll(){
        return tourRepository.findAll();
    }

    @GetMapping("/{tourId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${TourController.Tour}", response = Tour.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "Tour doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public EntityModel<Tour> getTourById(@PathVariable long tourId) {
        Tour tour= tourRepository.findById(tourId).get();
        if (null == tour)
            throw new PropertyNotFound("Tour not found");
        EntityModel model = EntityModel.of(tour);
        Link link = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll()).withRel("all-Tour");
        model.add(link);
        return model;
    }

    @PostMapping("/add/{propertyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${TourController.addTour}", response = Tour.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "Property doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<Object> saveTour(@PathVariable Long propertyId, @Valid @RequestBody Tour tour) {
        Property property = propertyRepository.findById(propertyId).get();
        if (null == property) {
            throw new PropertyNotFound("User Not Found");
        }
        tour.setProperty(property);
        tourRepository.save(tour);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{departmentId}")
                .buildAndExpand(property.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping("/delete/{tourId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${TourController.delete}", response = Tour.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "Tour doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public void deleteTour(@PathVariable Long tourId) {
        Tour tour = tourRepository.findById(tourId).get();
        if (null == tour)
            throw new PropertyNotFound("Tour not found");
        tourRepository.delete(tour);
    }
}
