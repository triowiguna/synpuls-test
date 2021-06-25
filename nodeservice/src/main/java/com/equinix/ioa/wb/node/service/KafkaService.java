package com.equinix.ioa.wb.node.service;

import com.equinix.ioa.wb.node.model.Account;
import com.equinix.ioa.wb.node.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaService {

    @Autowired
    AccountRepository accountRepository;

    @KafkaListener(
            topics = "accounts",
            containerFactory = "accountKafkaListenerContainerFactory")
    public void listenGroup(String topicName, List<Account> accounts) {
        accountRepository.saveAll(accounts);
    }

    public Page<Account> getAllAccountsPageable(Integer page, Integer limit){
        return accountRepository.findAll(PageRequest.of(page,limit));
    }
}
