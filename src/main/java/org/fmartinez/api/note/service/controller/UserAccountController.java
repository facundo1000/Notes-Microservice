package org.fmartinez.api.note.service.controller;

import jakarta.validation.Valid;
import org.fmartinez.api.note.service.entity.UserAccount;
import org.fmartinez.api.note.service.swagger.delete.ResponseUserAccountDelete;
import org.fmartinez.api.note.service.swagger.find.ResponseUserAccountFind;
import org.fmartinez.api.note.service.swagger.save.ResponseUserAccountSave;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
public interface UserAccountController {

    @GetMapping("/list")
    @ResponseUserAccountFind
    ResponseEntity<List<UserAccount>> findAllUsers();

    @GetMapping("/id/{id}")
    @ResponseUserAccountFind
    ResponseEntity<UserAccount> findUserById(@PathVariable String id);

    @PostMapping("/create")
    @ResponseUserAccountSave
    ResponseEntity<?> create(@RequestBody @Valid UserAccount user, BindingResult result);

    @PostMapping("/note/{idNote}/user/{idUser}")
    @ResponseUserAccountSave
    ResponseEntity<?> addNoteToUser(@PathVariable String idNote, @PathVariable String idUser);

    @PutMapping("/update/{id}")
    @ResponseUserAccountSave
    ResponseEntity<?> update(@RequestBody @Valid UserAccount user, BindingResult result, @PathVariable String id);

    @DeleteMapping("/delete/{id}")
    @ResponseUserAccountDelete
    ResponseEntity<Void> deleteUserById(@PathVariable String id);

}
