package com.managecustomers.security.user;


import com.managecustomers.security.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class UserController {
    private final AuthenticationService service;

    //Display welcome with the info of the server site
    @GetMapping("/")
    public String welcome() {
        return ("<h1>Welcome in the backend CRUD, SPRING SECURITY with JWT used on SmartLuggage </h1>");
    }


    //printing list of the users GET method
    @GetMapping("/displayUsers")
    public List<User> getUsers() {
        return service.getAllUsers();
    }


    //**********   DELETE     *******************************************
    @DeleteMapping(path = "/displayUsers/{userId}")
    public void deleteUser(  @PathVariable("userId") Integer userId){
        service.deleteUser(userId);
    }


    //**********   PUT      *******************************************
    // put update data in the customer for example we want ot change name and email
    @PutMapping("/displayUsers/{userId}")
    public void updateUser(@PathVariable("userId") Integer userId,
                               @RequestBody User request){
            service.updateUser(userId,request);
    }





}
