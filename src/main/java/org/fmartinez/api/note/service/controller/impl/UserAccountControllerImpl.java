package org.fmartinez.api.note.service.controller.impl;

import lombok.RequiredArgsConstructor;
import org.fmartinez.api.note.service.controller.UserAccountController;
import org.fmartinez.api.note.service.dto.UserResponse;
import org.fmartinez.api.note.service.entity.UserAccount;
import org.fmartinez.api.note.service.service.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserAccountControllerImpl implements UserAccountController {

    private final UserAccountService service;

    @Override
    public ResponseEntity<List<UserAccount>> findAllUsers() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<UserAccount> findUserById(String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    public ResponseEntity<?> create(UserAccount user, BindingResult result) {

        Map<String, String> errors = new HashMap<>();

        if (!result.hasErrors()) {
            return new ResponseEntity<>(service.create(user), HttpStatus.CREATED);
        }
        result.getFieldErrors()
                .forEach(field -> errors.put("error", field.getDefaultMessage()));

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> update(UserAccount user, BindingResult result, String id) {
        Map<String, String> errors = new HashMap<>();
        UserResponse response;

        if (!result.hasErrors()) {
            service.update(user, id);
            response = new UserResponse(user.getUsername(), user.getEmail());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        result.getFieldErrors()
                .forEach(field -> errors.put("error", field.getDefaultMessage()));

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> addNoteToUser(String idNote, String idUser) {
        UserAccount account = service.addNote(idNote, idUser);
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUserById(String id) {
        if (id != null) {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return null;
    }
}
