package org.fmartinez.api.note.service.controller;

import jakarta.validation.Valid;
import org.fmartinez.api.note.service.entity.UserAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
public interface UserAccountController {

    @GetMapping("/list")
    ResponseEntity<List<UserAccount>> findAllUsers();

    @GetMapping("/id/{id}")
    ResponseEntity<UserAccount> findUserById(@PathVariable String id);

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody @Valid UserAccount user, BindingResult result);

    @PostMapping("/note/{idNote}/user/{idUser}")
    ResponseEntity<?> addNoteToUser(@PathVariable String idNote, @PathVariable String idUser);

    @PutMapping("/update/{id}")
    ResponseEntity<?> update(@RequestBody @Valid UserAccount user, BindingResult result, @PathVariable String id);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> deleteUserById(@PathVariable String id);

}
