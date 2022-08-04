package com.team200.spyglassserver.domain.user.controller;

import com.team200.spyglassserver.domain.user.model.User;
import com.team200.spyglassserver.domain.user.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UseruCreateRequest user){
        UserDTO
                userDTO = userService.create(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<User>getById(@PathVariable("id")Long id){
        UserDTO user= userService.getById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("lookup")
    public ResponseEntity<User>getByEmail(@RequestParam String email){
        UserDTO user  = userService.getByEmail(email);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<User(@PathVariable("id")Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }




}
