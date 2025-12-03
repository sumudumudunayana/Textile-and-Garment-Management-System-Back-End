package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserAccount;
import org.example.entity.UserAccountEntity;
import org.example.repository.UserAccountRepository;
import org.example.service.UserAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<UserAccount> getAll() {
        List<UserAccount> UserAccountArrayList = new ArrayList<>();
        repository.findAll().forEach(entity->{
            UserAccountArrayList.add(mapper.map(entity, UserAccount.class));
        });
        return UserAccountArrayList;
    }

    @Override
    public void addUserAccount(UserAccount userAccount) {
        System.out.println(userAccount);
        repository.save(mapper.map(userAccount, UserAccountEntity.class));
    }

    @Override
    public void deleteUserAccountById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public UserAccount searchUserAccountById(Integer id) {
        return mapper.map(repository.findById(id),UserAccount.class);


    }

    @Override
    public void updateUserAccountById(UserAccount userAccount) {
        repository.save(mapper.map(userAccount, UserAccountEntity.class));
    }

    @Override
    public Optional<UserAccount> authenticate(String username, String password) {
        return repository.findByUserLoginNameAndUserLoginPassword(username, password)
                .map(entity -> mapper.map(entity, UserAccount.class));
    }
}
