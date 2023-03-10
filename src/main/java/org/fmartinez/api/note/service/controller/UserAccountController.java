package org.fmartinez.api.note.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.fmartinez.api.note.service.entity.UserAccount;
import org.fmartinez.api.note.service.exception.NotFoundException;
import org.fmartinez.api.note.service.swagger.delete.ResponseUserAccountDelete;
import org.fmartinez.api.note.service.swagger.find.ResponseFindAllUser;
import org.fmartinez.api.note.service.swagger.find.ResponseFindByEmailUser;
import org.fmartinez.api.note.service.swagger.find.ResponseFindByIdUser;
import org.fmartinez.api.note.service.swagger.save.ResponseUserAccountSave;
import org.fmartinez.api.note.service.swagger.save.ResponseUserAddNote;
import org.fmartinez.api.note.service.swagger.save.ResponseUserUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.fmartinez.api.note.service.constant.ApplicationConstant.USER_PATH;

@Tag(name = "User-Account Controller", description = "User-Account side operations")
@RequestMapping(USER_PATH)
public interface UserAccountController {


    @ResponseFindAllUser
    @GetMapping("/list")
    ResponseEntity<List<UserAccount>> findAllUsers();


    @ResponseFindByIdUser
    @GetMapping("/id/{id}")
    ResponseEntity<UserAccount> findUserById(@PathVariable String id);


    @ResponseFindByEmailUser
    @GetMapping("/email/{email}")
    ResponseEntity<UserAccount> findUserByEmail(@PathVariable String email);


    @ResponseUserAccountSave
    @PostMapping("/create")
    ResponseEntity<?> create(@Valid @RequestBody UserAccount user, BindingResult result);


    @ResponseUserAddNote
    @PostMapping("/note/{idNote}/user/{idUser}")
    ResponseEntity<?> addNoteToUser(@PathVariable String idNote, @PathVariable String idUser);


    @ResponseUserUpdate
    @PutMapping("/update/{id}")
    ResponseEntity<?> update(@RequestBody @Valid UserAccount user, BindingResult result, @PathVariable String id);

    @DeleteMapping("/delete/{id}")
    @ResponseUserAccountDelete
    ResponseEntity<Void> deleteUserById(@PathVariable String id);

}

