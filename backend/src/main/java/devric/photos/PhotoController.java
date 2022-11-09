package devric.photos;

import devric.appuser.AppUser;
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
@RequestMapping("/api/photo")
public class PhotoController {
    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    PropertyRepository propertyRepository;
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${PhotoController.all}", response = Photo.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<Photo> getAll(){
        return photoRepository.findAll();
    }

    @GetMapping("/{photoId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${PhotoController.photo}", response = Photo.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "Photo doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public EntityModel<Photo> getPhotoById(@PathVariable long photoId) {
        Photo photo= photoRepository.findById(photoId).get();
        if (null == photo)
            throw new PropertyNotFound("photo not found");
        EntityModel model = EntityModel.of(photo);
        Link link = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll()).withRel("all-photo");
        model.add(link);
        return model;
    }


    @PostMapping("/add/{propertyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${PhotoContoller.addphoto}", response = Photo.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "Property doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<Object> savePhoto(@PathVariable Long propertyId, @Valid @RequestBody Photo photo) {
        Property property = propertyRepository.findById(propertyId).get();
        if (null == property) {
            throw new PropertyNotFound("User Not Found");
        }
        photo.setProperty(property);
        photoRepository.save(photo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{departmentId}")
                .buildAndExpand(property.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping("/delete/{photoId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${PhotoContoller.delete}", response = Photo.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "Property doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public void deletePhoto(@PathVariable Long photoId) {
        Photo photo = photoRepository.findById(photoId).get();
        if (null == photo)
            throw new PropertyNotFound("photo not found");
        photoRepository.delete(photo);
    }
}

