package com.hit.security.service.impl;

import com.hit.security.dto.AccountDTO;
import com.hit.security.dto.RoleDTO;
import com.hit.security.exception.DuplicateException;
import com.hit.security.exception.NotFoundException;
import com.hit.security.models.Account;
import com.hit.security.models.Role;
import com.hit.security.repository.AccountRepository;
import com.hit.security.repository.RoleRepository;
import com.hit.security.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Account saveAccount(AccountDTO accountDTO) {
        Account account = mapper.map(accountDTO, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public void addRoleToAccount(String username, String roleName) {
        Account account = accountRepository.findByUsername(username);
        Role role = roleRepository.findByRoleName(roleName);
        if (account == null) {
            throw new NotFoundException("Not Found Username");
        } else if (role == null) {
            throw new NotFoundException("Not Found Role");
        }

        Set<Role> roles = account.getRoles();
        if(roles.contains(role)) {
            throw new DuplicateException("This account already has this role");
        }
        roles.add(role);
        account.setRoles(roles);
        accountRepository.save(account);

        Set<Account> accounts = role.getAccounts();
        accounts.add(account);
        role.setAccounts(accounts);
        roleRepository.save(role);
    }

    @Override
    public Account getAcc(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
