package devric.message;

import devric.appuser.AppUser;
import devric.appuser.UserRepository;
import devric.exception_handler.PropertyNotFound;
import devric.message.Message;
import devric.message.MessageRepository;
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
    MessageRepository messageRepository;
    @Autowired
    UserRepository appUserRepository;
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${MessageController.all}", response = Message.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<Message> getAll(){
        return messageRepository.findAll();
    }

    @GetMapping("/{messageId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${MessageController.message}", response = Message.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "Message doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public EntityModel<Message> getMessageById(@PathVariable long messageId) {
        Message message = messageRepository.findById(messageId).get();
        if (null == message)
            throw new PropertyNotFound("message not found");
        EntityModel model = EntityModel.of(message);
        Link link = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll()).withRel("all-properies");
        model.add(link);
        return model;
    }

    @PostMapping("/add/{userid}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${MessageController.addmessage}", response = Message.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "User doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<Object> saveMessage(@PathVariable Integer userid, @Valid @RequestBody Message message) {
        AppUser appUser = appUserRepository.findById(userid).get();
        if (null == appUser) {
            throw new PropertyNotFound("User Not Found");
        }
        message.setAppUser(appUser);
        messageRepository.save(message);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{departmentId}")
                .buildAndExpand(appUser.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping("/delete/{messageId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${MessageController.delete}", response = Message.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "Message doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public void deleteMessage(@PathVariable Long messageId) {
        Message message = messageRepository.findById(messageId).get();
        if (null == message)
            throw new PropertyNotFound("message not found");
        messageRepository.delete(message);
    }
}
