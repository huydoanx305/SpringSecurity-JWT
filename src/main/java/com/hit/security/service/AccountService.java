package com.hit.security.service;

import com.hit.security.dto.AccountDTO;
import com.hit.security.models.Account;

import java.util.List;

public interface AccountService {
    Account saveAccount(AccountDTO accountDTO);
    void addRoleToAccount(String username, String roleName);
    Account getAcc(String username);
    List<Account> getAccounts();
}
