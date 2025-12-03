package org.example.service;

import org.example.dto.UserAccount;

import java.util.List;
import java.util.Optional;

public interface UserAccountService {
    List<UserAccount> getAll();

    void addUserAccount(UserAccount userAccount);

    void deleteUserAccountById(Integer id);

    UserAccount searchUserAccountById(Integer id);

    void updateUserAccountById(UserAccount userAccount);

    Optional<UserAccount> authenticate(String username, String password);
}
