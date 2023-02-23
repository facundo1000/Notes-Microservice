package org.fmartinez.api.note.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.fmartinez.api.note.service.entity.Note;
import org.fmartinez.api.note.service.entity.UserAccount;
import org.fmartinez.api.note.service.repository.NotesRepository;
import org.fmartinez.api.note.service.repository.UserAccountRepository;
import org.fmartinez.api.note.service.service.UserAccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository repository;

    private final NotesRepository noteRepo;

    @Override
    public List<UserAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public UserAccount findById(String id) {
        return repository.findById(id)
                .orElseThrow();
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        return repository.save(userAccount);
    }

    @Override
    public UserAccount update(UserAccount user, String id) {

        List<Note> notes = user.getNote();

        if (id != null) {
            Optional<UserAccount> account = repository.findById(id);

            if (account.isPresent()) {
                account.get().setUsername(user.getUsername());
                account.get().setPassword(user.getPassword());
                account.get().setEmail(user.getEmail());
                account.get().setNote(notes);
                return repository.save(account.get());
            }
            return account.get();
        }
        throw new NoSuchElementException("id not found in the system");
    }

    @Override
    public UserAccount addNote(String idNote, String idUser) {
        Optional<Note> note = noteRepo.findById(idNote);
        Optional<UserAccount> account = repository.findById(idUser);

        if (note.isPresent() && account.isPresent()) {
            account.get().addNote(note.get());
            noteRepo.save(note.get());
            return repository.save(account.get());
        }
        throw new NoSuchElementException("elements don't found");
    }

    @Override
    public Void deleteById(String id) {
        if (id != null) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException("id not found in the system");
        }
        return null;
    }
}
