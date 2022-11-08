package devric.message;

import devric.appuser.AppUser;
import devric.appuser.UserRepository;
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

public class MessageController {
    @Autowired
    MessageRepository propertyRepository;
    @Autowired
    UserRepository appUserRepository;
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${PropertyController.all}", response = Property.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<Property> getAll(){
        return propertyRepository.findAll();
    }

    @GetMapping("/{propertyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${PropertyController.property}", response = Property.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "Property doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public EntityModel<Property> getPropertyById(@PathVariable long propertyId) {
        Property property = propertyRepository.findById(propertyId).get();
        if (null == property)
            throw new PropertyNotFound("property not found");
        EntityModel model = EntityModel.of(property);
        Link link = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll()).withRel("all-properies");
        model.add(link);
        return model;
    }

    @PostMapping("/add/{userid}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${PropertyController.addproperty}", response = Property.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "User doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<Object> saveProperty(@PathVariable Integer userid, @Valid @RequestBody Property property) {
        AppUser appUser = appUserRepository.findById(userid).get();
        if (null == appUser) {
            throw new PropertyNotFound("User Not Found");
        }
        property.setUser(appUser);
        propertyRepository.save(property);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{departmentId}")
                .buildAndExpand(appUser.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping("/delete/{propertyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${PropertyController.delete}", response = Property.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "Property doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public void deleteProperty(@PathVariable Long propertyId) {
        Property property = propertyRepository.findById(propertyId).get();
        if (null == property)
            throw new PropertyNotFound("property not found");
        propertyRepository.delete(property);
    }
}
