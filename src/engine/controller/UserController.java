package engine.controller;

import engine.Dto.UserDto;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/api/register")
    public void registerUser(@Valid @RequestBody UserDto userDto) {
        userService.register(userDto.getEmail(), userDto.getPassword());
    }
}
