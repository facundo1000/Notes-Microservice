package org.fmartinez.api.note.service.controller;

import org.fmartinez.api.note.service.controller.impl.UserAccountControllerImpl;
import org.fmartinez.api.note.service.dto.user.UserResponse;
import org.fmartinez.api.note.service.entity.Note;
import org.fmartinez.api.note.service.entity.UserAccount;
import org.fmartinez.api.note.service.service.UserAccountService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fmartinez.api.note.service.util.PojoGenerator.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAccountControllerTest {

    @Mock
    UserAccountService service;

    @InjectMocks
    UserAccountControllerImpl controller;

    private static UserAccount user;
    private static Note note;
    private static BindingResult result;

    private static UserResponse userResponse;


    @BeforeAll
    static void setUp() {
        user = buildUserAccount();
        note = buildNoteModel();
        result = new BeanPropertyBindingResult(user, "user");
        userResponse = buildUserResponse();
    }

    @Test
    @DisplayName("Test: get users response [200]")
    void getAllUsers() {
        when(service.findAll()).thenReturn(List.of(user));
        ResponseEntity<List<UserAccount>> response = controller.findAllUsers();

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isInstanceOf(List.class);
        assertThat(response.getBody()).hasSize(response.getBody().size());

        verify(service).findAll();
    }

    @Test
    @DisplayName("Test: get user by id response [200]")
    void getUserById() {
        when(service.findById(anyString())).thenReturn(user);

        ResponseEntity<UserAccount> response = controller.findUserById(user.getId());

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .isNotNull()
                .isInstanceOf(UserAccount.class)
                .extracting(UserAccount::getId)
                .isEqualTo(user.getId());

        verify(service).findById(anyString());
    }

    @Test
    @DisplayName("Test: get user by email response [200]")
    void getUserByEmail() {
        when(service.findUserByEmail(anyString())).thenReturn(user);

        ResponseEntity<UserAccount> response = controller.findUserByEmail("davinci@monalisa.com");

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().isInstanceOf(UserAccount.class);


        verify(service).findUserByEmail(anyString());
    }

    @Test
    @DisplayName("Test: create new user response [201]")
    void createUser() {
        //TODO: corregir
        when(service.create(any(UserAccount.class))).thenReturn(userResponse);

        ResponseEntity<?> response = controller.create(user, result);

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull().isInstanceOf(UserResponse.class);

        verify(service).create(any(UserAccount.class));
    }

    @Test
    @DisplayName("Test: update user response [200]")
    void updateUser() {
        when(service.update(any(UserAccount.class), anyString())).thenReturn(user);

        ResponseEntity<?> response = controller.update(user, result, user.getId());

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().isInstanceOf(UserAccount.class);

        verify(service).update(any(UserAccount.class), anyString());
    }

    @Test
    @DisplayName("Test: add note to user response [200]")
    void addNoteToUser() {
        when(service.addNote(anyString(), anyString())).thenReturn(user);

        ResponseEntity<?> response = controller.addNoteToUser(user.getId(), note.getId());

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().isInstanceOf(UserAccount.class);

        verify(service).addNote(anyString(), anyString());
    }

    @Test
    @DisplayName("Test: delete user by id response [204]")
    void deleteUser() {
        ResponseEntity<Void> response = controller.deleteUserById(user.getId());

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();

        verify(service).deleteById(anyString());
    }
}
