package org.fmartinez.api.note.service.service;

import org.fmartinez.api.note.service.dto.user.UserRequest;
import org.fmartinez.api.note.service.dto.user.UserResponse;
import org.fmartinez.api.note.service.entity.UserAccount;

import java.util.List;

public interface UserAccountService {

    List<UserAccount> findAll();

    UserAccount findById(String id);

    UserAccount findUserByEmail(String email);

    UserResponse create(UserAccount user);

    UserAccount addNote(String idNote, String idUser);

    UserAccount update(UserAccount userAccount, String id);

    Void deleteById(String id);

}
