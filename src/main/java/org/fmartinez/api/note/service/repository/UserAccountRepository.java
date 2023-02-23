package org.fmartinez.api.note.service.repository;

import org.fmartinez.api.note.service.entity.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {
    Optional<UserAccount> findUserAccountByEmail(String email);
}
