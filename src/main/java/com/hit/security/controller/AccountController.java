package com.hit.security.controller;

import com.hit.security.base.BaseController;
import com.hit.security.dto.AccountDTO;
import com.hit.security.dto.RoleToAccountForm;
import com.hit.security.models.Account;
import com.hit.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController extends BaseController<Account> {
    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts() {
        return this.resListSuccess(accountService.getAccounts());
    }

    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO accountDTO) {
        return this.resSuccess(accountService.saveAccount(accountDTO));
    }

    @PostMapping("/role/addtoaccount")
    public ResponseEntity<?> addRoleToAccount(@RequestBody RoleToAccountForm form) {
        if(form.getUsername() == null || form.getRoleName() == null) {
            return this.resFailed();
        }
        accountService.addRoleToAccount(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().body("add Role success");
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id) {
        return this.resSuccess(accountService.deleteAccount(id));
    }
}
