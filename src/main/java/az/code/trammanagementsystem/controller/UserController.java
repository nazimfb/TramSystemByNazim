package az.code.trammanagementsystem.controller;

import az.code.trammanagementsystem.dto.UserDTO;
import az.code.trammanagementsystem.entity.User;
import az.code.trammanagementsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://192.168.1.69:8000")
public class UserController {
    private final UserService service;
    private final ModelMapper mapper;

    @PostMapping
    private ResponseEntity<UserDTO> createNewUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(
                mapper.map(
                        service.createUser(mapper.map(userDTO, User.class)),
                        UserDTO.class)
        );
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(
                mapper.map(
                        service.getUserById(id),
                        UserDTO.class)
        );
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
