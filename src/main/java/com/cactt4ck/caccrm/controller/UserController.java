package com.cactt4ck.caccrm.controller;

import com.cactt4ck.caccrm.model.CacAccount;
import com.cactt4ck.caccrm.service.CacAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {

    public static final String BEFORE_GET = "[Before GET]";
    public static final String AFTER_GET = "[After GET]";

    private final CacAccountService cacAccountService;

    public UserController(CacAccountService cacAccountService) {
        this.cacAccountService = cacAccountService;
    }

    @GetMapping("/me")
    public ResponseEntity<CacAccount> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("{} - {}", BEFORE_GET, authentication.getDetails());

        CacAccount account = (CacAccount) authentication.getPrincipal();

        log.info("{} - {}", AFTER_GET, account);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/")
    public ResponseEntity<List<CacAccount>> allUsers() {
        List <CacAccount> users = cacAccountService.findAll();

        return ResponseEntity.ok(users);
    }

    /*@GetMapping("/accounts")
    public ResponseEntity<List<CacAccount>> getAccounts() {
        log.info("Retrieved all accounts");
        return ResponseEntity.ok(cacAccountService.findAll());
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<CacAccount> getCacAccount(@PathVariable String id) {
        log.info("[GET REQUEST]");
        UUID uuid = UUID.fromString(id);
        return cacAccountService.findById(uuid).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/account")
    public ResponseEntity<CacAccount> createAccount(@RequestBody CacAccount cacAccount) {
        return ResponseEntity.ok(cacAccountService.saveCacAccount(cacAccount));
    }

    @GetMapping("/account/verify_password/{id}")
    public ResponseEntity<Boolean> verifyPassword(@PathVariable String id, @RequestBody String password) {
        log.info("[GET REQUEST]");
        UUID uuid = UUID.fromString(id);
        return ResponseEntity.ok(cacAccountService.passwordIsOk(uuid, password));
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        log.info("[DELETE REQUEST]");
        UUID uuid = UUID.fromString(id);
        cacAccountService.deleteById(uuid);
        return ResponseEntity.noContent().build();
    }*/

}
