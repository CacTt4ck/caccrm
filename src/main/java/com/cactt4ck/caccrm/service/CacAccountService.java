package com.cactt4ck.caccrm.service;

import com.cactt4ck.caccrm.model.CacAccount;
import com.cactt4ck.caccrm.repository.CacAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CacAccountService {

    private final CacAccountRepository cacAccountRepository;

    public CacAccountService(CacAccountRepository cacAccountRepository) {
        this.cacAccountRepository = cacAccountRepository;
    }

    public Optional<CacAccount> findById(UUID id) {
        return cacAccountRepository.findById(id);
    }

    public List<CacAccount> findAll() {
        return cacAccountRepository.findAll();
    }

    public void deleteById(UUID id) {
        cacAccountRepository.deleteById(id);
    }

}
