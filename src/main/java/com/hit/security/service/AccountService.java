package com.hit.security.service;

import com.hit.security.dto.AccountDTO;
import com.hit.security.models.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAccounts();
    Account getAcc(String username);
    Account saveAccount(AccountDTO accountDTO);
    void addRoleToAccount(String username, String roleName);
    Account deleteAccount(Long id);
}
