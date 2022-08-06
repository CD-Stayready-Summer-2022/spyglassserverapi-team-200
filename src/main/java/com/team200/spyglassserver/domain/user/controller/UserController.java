package com.team200.spyglassserver.domain.user.controller;

import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;
import com.team200.spyglassserver.domain.user.dtos.UserCreateRequest;
import com.team200.spyglassserver.domain.user.dtos.UserDTO;
import com.team200.spyglassserver.domain.user.model.User;
import com.team200.spyglassserver.domain.user.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.apache.tomcat.util.http.parser.HttpParser;
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
    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserCreateRequest user){
        UserDTO userDTO = userService.create(user);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<UserDTO>getById(@PathVariable("id")Long id){
        UserDTO user= userService.getById(String.valueOf(id));
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("lookup")
    public ResponseEntity<UserDTO>getByEmail(@RequestParam String email){
        UserDTO user  = userService.getByEmail(email);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO>update(@PathVariable String id, @RequestBody User user){
        try{
            UserDTO updatedUserDTO = userService.update(id, user);
            ResponseEntity response = new ResponseEntity(updatedUserDTO, HttpStatus.OK);
            return response;
        }catch(ResourceNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();

        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete (@PathVariable ("id") Long id){
        userService.delete(String.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }




}
