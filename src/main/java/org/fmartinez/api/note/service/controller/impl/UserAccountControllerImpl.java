package org.fmartinez.api.note.service.controller.impl;

import lombok.RequiredArgsConstructor;
import org.fmartinez.api.note.service.controller.UserAccountController;
import org.fmartinez.api.note.service.entity.UserAccount;
import org.fmartinez.api.note.service.service.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public ResponseEntity<UserAccount> findUserByEmail(String email) {
        return ResponseEntity.ok(service.findUserByEmail(email));
    }

    @Override
    public ResponseEntity<?> create(UserAccount user, BindingResult result) {
        try {
            Map<String, String> errors = new HashMap<>();

            if (result.hasErrors()) {
                result.getFieldErrors()
                        .forEach(field -> errors.put(field.getField(), "Field "
                                .concat(field.getField())
                                .concat(" ")
                                .concat(Objects.requireNonNull(field.getDefaultMessage()))));

                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(service.create(user), HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UserAccount creation error");
        }
    }

    @Override
    public ResponseEntity<?> update(UserAccount user, BindingResult result, String id) {
        try {
            if (result.hasErrors()) {
                Map<String, String> errors = new HashMap<>();
                result.getFieldErrors()
                        .forEach(field -> errors.put(field.getField(), "Field "
                                .concat(field.getField())
                                .concat(" ")
                                .concat(Objects.requireNonNull(field.getDefaultMessage()))));

                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(service.update(user, id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UserAccount update error");
        }
    }

    @Override
    public ResponseEntity<?> addNoteToUser(String idNote, String idUser) {
        UserAccount account = service.addNote(idNote, idUser);
        return new ResponseEntity<>(account, HttpStatus.OK);
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
