package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.LoginRequest;
import org.example.dto.LoginResponse;
import org.example.dto.UserAccount;
import org.example.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user_account")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class UserAccountController {
    @Autowired
    final UserAccountService service;

    @GetMapping("/get-all")
    public List<UserAccount> getUserAccount(){
        return service.getAll();
    }

    @PostMapping("/add-user-account")
    public void addUserAccount(@RequestBody UserAccount userAccount){
        log.info("updated-> {}",userAccount);
        service.addUserAccount(userAccount);
    }

    @GetMapping("/search-by-id/{id}")
    public UserAccount getUserAccountById(@PathVariable Integer id){
        return service.searchUserAccountById(id);
    }

    @DeleteMapping("/delete-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUserAccountById(@PathVariable Integer id){
        service.deleteUserAccountById(id);
    }
    @PutMapping("/update-user-account")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUserAccount(@RequestBody UserAccount userAccount){
        service.updateUserAccountById(userAccount);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        return service.authenticate(req.getUsername(), req.getPassword())
                .map(u -> ResponseEntity.ok(new LoginResponse(u.getId(), u.getUserName(), u.getUserRole())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}
