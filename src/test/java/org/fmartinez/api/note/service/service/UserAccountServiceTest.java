package org.fmartinez.api.note.service.service;

import org.fmartinez.api.note.service.dto.user.UserResponse;
import org.fmartinez.api.note.service.entity.Note;
import org.fmartinez.api.note.service.entity.UserAccount;
import org.fmartinez.api.note.service.mapper.MapStructMapper;
import org.fmartinez.api.note.service.repository.NotesRepository;
import org.fmartinez.api.note.service.repository.UserAccountRepository;
import org.fmartinez.api.note.service.service.impl.UserAccountServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fmartinez.api.note.service.util.PojoGenerator.buildNoteModel;
import static org.fmartinez.api.note.service.util.PojoGenerator.buildUserAccount;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAccountServiceTest {

    @Mock
    UserAccountRepository repository;

    @Mock
    NotesRepository repoNote;

    @Mock
    MapStructMapper mapper;

    @InjectMocks
    UserAccountServiceImpl service;

    private static UserAccount user;


    private static Note note;

    @BeforeAll
    static void SetUp() {
        user = buildUserAccount();
        note = buildNoteModel();
    }


    @Test
    @DisplayName("Test: get all users")
    void listAllUsers() {
        when(repository.findAll()).thenReturn(List.of(new UserAccount()));
        List<UserAccount> users = service.findAll();

        UserAccount userDto = users.iterator().next();

        assertThat(users).isNotEmpty();
        assertThat(users).hasSize(users.size());
        assertThat(users).isInstanceOf(List.class);

        verify(repository).findAll();

    }

    @Test
    @DisplayName("Test: get user by id")
    void getUserById() {
        when(repository.findById(anyString())).thenReturn(Optional.of(user));
        UserAccount user = service.findById("64f36t0f956e323cb9b9fp35");

        assertThat(user).isNotNull();
        assertThat(user).isInstanceOf(UserAccount.class);

        verify(repository).findById(anyString());
    }

    @Test
    @DisplayName("Test: create a new user")
    void createUser() {
        when(repository.save(any(UserAccount.class))).thenReturn(new UserAccount());
        when(mapper.mapToUserResponse(any(UserAccount.class))).thenReturn(new UserResponse());
        UserResponse newUser = service.create(user);

        assertThat(newUser).isNotNull();
        assertThat(user.getId()).isNotBlank();
        assertThat(user.getUsername()).isNotBlank();
        assertThat(user.getEmail()).isNotBlank();
        assertThat(user.getPassword()).isNotBlank();

        verify(repository).save(any(UserAccount.class));
        verify(mapper).mapToUserResponse(any(UserAccount.class));
    }

    @Test
    @DisplayName("Test: update a user by id")
    void updateUser() {
        when(repository.save(any(UserAccount.class))).thenReturn(user);
        when(repository.findById(anyString())).thenReturn(Optional.of(user));

        UserAccount updateUser = service.update(user, "64f36t0f956e323cb9b9fp35");

        assertThat(updateUser).isNotNull();
        assertThat(updateUser.getId()).isEqualTo("64f36t0f956e323cb9b9fp35");
        assertThat(updateUser.getEmail()).isNotBlank();
        assertThat(updateUser.getUsername()).isNotBlank();
        assertThat(updateUser.getPassword()).isNotBlank();
//        assertThat(updateUser.getNote()).isNotNull().hasSize(user.getNote().size());

        verify(repository).save(any(UserAccount.class));
        verify(repository).findById(anyString());
    }

    @Test
    @DisplayName("Test: get user by email")
    void getUserByEmail() {
        when(repository.findUserAccountByEmail(anyString())).thenReturn(Optional.of(user));
        UserAccount userAccount = service.findUserByEmail("davinci@monalisa.com");

        assertThat(userAccount).isNotNull();
        assertThat(userAccount).isInstanceOf(UserAccount.class);
        assertThat(userAccount.getEmail()).isEqualTo("davinci@monalisa.com");

        verify(repository).findUserAccountByEmail(anyString());
    }

    @Test
    @DisplayName("Test: add a note to a user")
    void addNoteToUser() {
        when(repository.save(any(UserAccount.class))).thenReturn(user);
        when(repository.findById(anyString())).thenReturn(Optional.of(user));
        when(repoNote.findById(anyString())).thenReturn(Optional.of(note));

        UserAccount userNote = service.addNote(user.getId(), note.getId());

        assertThat(userNote).isNotNull();
        assertThat(userNote.getId()).isEqualTo(user.getId());
//        assertThat(userNote.getNote()).isNotNull().hasSize(userNote.getNote().size()).contains(note);

        verify(repository).findById(anyString());
        verify(repository).findById(anyString());
        verify(repository).save(any(UserAccount.class));
    }

    @Test
    @DisplayName("Test: delete user by id")
    void deleteUserById() {
        Void deleted = service.deleteById(user.getId());

        assertThat(deleted).isNull();

        verify(repository).deleteById(anyString());
    }

}
